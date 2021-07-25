package com.bluecrow.order.service.impl;

import com.bluecrow.core.entity.PayEvent;
import com.bluecrow.order.dao.PayEventDao;
import com.bluecrow.order.service.PayEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BlueCrow
 * @Package com.bluecrow.order.service.impl
 * @Decription
 * @date 2021/7/24 21:04
 */
@Service
public class PayEventServiceImpl implements PayEventService {

    @Autowired
    private PayEventDao payEventDao;

    @Override
    public int insert(PayEvent payEvent) {
        return payEventDao.insert(payEvent);
    }
}
