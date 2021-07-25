package com.bluecrow.core.entity;

/**
 * @author BlueCrow
 * @Package com.bluecrow.core.entity
 * @Decription
 * @date 2021/7/25 12:29
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_order_event
 * @author
 */
@Data
public class PayEvent implements Serializable {
    /**
     * 订单id
     */
    private Long payId;

    /**
     * 同步标识（1-同步；2-异步）
     */
    private Integer syncFlag;

    /**
     * 事件类型事件类型（0-初始化；1-已发送）
     */
    private Integer orderType;

    /**
     * 事件环节（new,published,processed)
     */
    private String process;

    /**
     * 事件内容，保存事件发生时需要传递的数据
     */
    private String content;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *  更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}