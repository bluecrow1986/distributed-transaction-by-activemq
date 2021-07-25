package com.bluecrow.pay.service;

import java.math.BigDecimal;

/**
 * @author BlueCrow
 * @Package com.bluecrow.pay.service
 * @Decription
 * @date 2021/7/25 14:04
 */
public interface PayService {

    /**
     * 创建支付订单
     * @return 订单号
     */
    Long createPay(String name, BigDecimal amount);

    /**
     * 支付服务
     * @param payId 支付订单号
     * @return 影响行数
     */
    Integer pay(long payId);

    /**
     * 完成支付
     * @param payId 支付订单号
     * @return 影响行数
     */
    Integer completePay(long payId);
}
