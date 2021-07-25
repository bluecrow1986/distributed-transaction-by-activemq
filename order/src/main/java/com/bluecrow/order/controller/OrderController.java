package com.bluecrow.order.controller;

import com.alibaba.fastjson.JSONObject;
import com.bluecrow.core.consts.PayStageEnum;
import com.bluecrow.core.consts.ResponseConst;
import com.bluecrow.core.entity.ResponseBo;
import com.bluecrow.core.util.HttpClientHelper;
import com.bluecrow.order.entity.Order;
import com.bluecrow.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author BlueCrow
 * @Package com.bluecrow.order.controller
 * @Decription
 * @date 2021/7/25 13:29
 */
@RestController
public class OrderController {

    private static final String BASE_PAY_URL = "http://localhost:5002/pay/";

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseBo<String> createOrder() {
        // 1. 请求支付服务创建订单
        String name = "测试订单";
        BigDecimal amount = BigDecimal.valueOf(100.03);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", name);
        params.add("amount", String.valueOf(amount));
        ResponseBo responseBo = HttpClientHelper.doPost(BASE_PAY_URL + "createPay", params);
        String payIdStr  = String.valueOf(responseBo.get(ResponseConst.DATA));
        if (!StringUtils.isEmpty(payIdStr)) {
            Order order = new Order();
            order.setName(name);
            order.setPayId(Long.parseLong(payIdStr));
            order.setAmount(amount);
            order.setStage(PayStageEnum.WAITING_PAY.getCode());
            orderService.createOrder(order);
        }
        return ResponseBo.ok(payIdStr);
    }
}
