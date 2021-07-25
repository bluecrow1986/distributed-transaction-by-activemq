package com.bluecrow.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * tb_order
 * @author 
 */
@Data
public class Order implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 支付订单号
     */
    private Long payId;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 状态：1-待支付；2-支付中；3-已支付
     */
    private Integer stage;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}