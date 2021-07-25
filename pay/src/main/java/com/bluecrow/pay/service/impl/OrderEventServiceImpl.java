package com.bluecrow.pay.service.impl;

import com.bluecrow.core.entity.PayEvent;
import com.bluecrow.pay.dao.PayEventDao;
import com.bluecrow.pay.service.PayEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BlueCrow
 * @Package com.bluecrow.pay.service.impl
 * @Decription
 * @date 2021/7/24 21:26
 */
@Service
public class OrderEventServiceImpl implements PayEventService {

    @Autowired
    private PayEventDao payEventDao;

    @Override
    public int insert(PayEvent record) {
        return payEventDao.insert(record);
    }

    @Override
    public List<PayEvent> selectByOrderType(Integer code) {
        return payEventDao.selectByOrderType(code);
    }

    @Override
    public List<PayEvent> selectByOrderTypeAndSyncFlag(Integer code, Integer syncFlag) {
        return payEventDao.selectByOrderTypeAndSyncFlag(code, syncFlag);
    }

    @Override
    public int updateSentSuccess(Integer originalOrderType, Integer syncFlag, Integer newOrderType) {
        return payEventDao.updateSentSuccess(originalOrderType, syncFlag, newOrderType);
    }
}
