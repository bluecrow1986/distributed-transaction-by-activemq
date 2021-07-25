package com.bluecrow.pay.service;

import com.bluecrow.core.entity.PayEvent;

import java.util.List;

/**
 * @author BlueCrow
 * @Package com.bluecrow.pay.service
 * @Decription
 * @date 2021/7/24 21:25
 */
public interface PayEventService {

    int insert(PayEvent record);

    /**
     * 根据事件类型获取实体列表
     * @param code 事件类型
     * @return 实体列表
     */
    List<PayEvent> selectByOrderType(Integer code);

    /**
     * 根据事件类型和同步标识获取实体列表
     * @param code 事件类型
     * @param syncFlag 同步标识
     * @return 实体列表
     */
    List<PayEvent> selectByOrderTypeAndSyncFlag(Integer code, Integer syncFlag);

    /**
     * 批量更新事件类型, 初始化 -> 已发送
     * @param originalOrderType 原始事件类型
     * @param syncFlag 同步标识
     * @param newOrderType 新的事件类型
     * @return 影响行数
     */
    int updateSentSuccess(Integer originalOrderType, Integer newOrderType, Integer syncFlag);
}
