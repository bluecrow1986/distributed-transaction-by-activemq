package com.bluecrow.order.dao;

import com.bluecrow.order.entity.Order;

public interface OrderDao {
    int insert(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Order record);

    int updateOrderStage(Order order);
}