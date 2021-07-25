package com.bluecrow.order.service;

import com.bluecrow.order.entity.Order;

/**
 * @author BlueCrow
 * @Package com.bluecrow.order.service
 * @Decription
 * @date 2021/7/25 14:31
 */
public interface OrderService {

    int createOrder(Order order);

    int updateOrderStage(Order order);
}
