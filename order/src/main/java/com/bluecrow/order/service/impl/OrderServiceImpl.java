package com.bluecrow.order.service.impl;

import com.bluecrow.order.dao.OrderDao;
import com.bluecrow.order.entity.Order;
import com.bluecrow.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BlueCrow
 * @Package com.bluecrow.order.service.impl
 * @Decription
 * @date 2021/7/25 14:31
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public int createOrder(Order order) {
        return orderDao.insert(order);
    }

    @Override
    public int updateOrderStage(Order order) {
        return orderDao.updateOrderStage(order);
    }
}
