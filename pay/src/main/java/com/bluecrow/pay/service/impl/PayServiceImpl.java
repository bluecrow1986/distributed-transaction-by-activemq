package com.bluecrow.pay.service.impl;

import com.bluecrow.core.consts.OrderTypeEnum;
import com.bluecrow.core.consts.PayStageEnum;
import com.bluecrow.core.consts.ResponseConst;
import com.bluecrow.core.consts.SyncFlagEnum;
import com.bluecrow.core.entity.PayEvent;
import com.bluecrow.core.entity.ResponseBo;
import com.bluecrow.core.util.HttpClientHelper;
import com.bluecrow.pay.dao.PayDao;
import com.bluecrow.pay.entity.Pay;
import com.bluecrow.pay.service.PayEventService;
import com.bluecrow.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigDecimal;

/**
 * @author BlueCrow
 * @Package com.bluecrow.pay.service.impl
 * @Decription
 * @date 2021/7/25 14:05
 */
@Service
public class PayServiceImpl implements PayService {

    private static final String BASE_PAY_URL = "http://localhost:5003/third-pay/";

    @Autowired
    private PayDao payDao;

    @Autowired
    private PayEventService payEventService;

    @Override
    @Transactional
    public Long createPay(String name, BigDecimal amount) {
        Pay pay = new Pay();
        pay.setName(name);
        pay.setAmount(amount);
        pay.setStage(PayStageEnum.WAITING_PAY.getCode());
        int nResult = payDao.insert(pay);
        if (nResult > 0) {
            return pay.getId();
        }
        return null;
    }

    @Override
    @Transactional
    public Integer pay(long payId) {
        // 1. 生成本地事件
        PayEvent payEvent = new PayEvent();
        payEvent.setPayId(payId);
        payEvent.setOrderType(OrderTypeEnum.INIT.getCode());
        payEvent.setSyncFlag(SyncFlagEnum.SYNC.getCode());
        payEvent.setContent("前端根据支付订单号调用支付服务");
        int nCount = payEventService.insert(payEvent);
        if (nCount <= 0) {
            // 抛出异常, 事务回滚, 后续中断
            throw new RuntimeException("生成本地事件失败");
        }
        // 2. 调用三方支付服务
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("payId", String.valueOf(payId));
        ResponseBo responseBo = HttpClientHelper.doPost(BASE_PAY_URL + "syncPay", params);
        Integer code  = (Integer) responseBo.get(ResponseConst.CODE);
        if (code == null || code != 200) {
            // 抛出异常, 事务回滚, 后续中断
            throw new RuntimeException("调用三方支付服务失败");
        }
        // 3. 更新支付订单(支付中)
        Pay pay = new Pay();
        pay.setId(payId);
        pay.setStage(PayStageEnum.PAY.getCode());

        return payDao.updateStageId(pay);
    }

    @Override
    @Transactional
    public Integer completePay(long payId) {
        // 1. 生成本地事件(主键为支付订单号+异步标识; 初始化)
        PayEvent payEvent = new PayEvent();
        payEvent.setPayId(payId);
        payEvent.setOrderType(OrderTypeEnum.INIT.getCode());
        payEvent.setSyncFlag(SyncFlagEnum.ASYNC.getCode());
        payEvent.setContent("三方支付回写支付状态(支付完成)");
        int nCount = payEventService.insert(payEvent);
        if (nCount <= 0) {
            // 抛出异常, 事务回滚, 后续中断
            throw new RuntimeException("生成本地事件失败");
        }
        // 2. 更新支付订单(已支付)
        Pay pay = new Pay();
        pay.setId(payId);
        pay.setStage(PayStageEnum.PAID.getCode());

        return payDao.updateStageId(pay);
    }
}
