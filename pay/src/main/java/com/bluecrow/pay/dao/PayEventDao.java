package com.bluecrow.pay.dao;

import com.bluecrow.core.entity.PayEvent;

import java.util.List;

public interface PayEventDao {
    int insert(PayEvent record);

    List<PayEvent> selectByOrderTypeAndSyncFlag(Integer orderType, Integer syncFlag);

    List<PayEvent> selectByOrderType(Integer orderType);

    int updateSentSuccess(Integer originalOrderType, Integer syncFlag, Integer newOrderType);
}