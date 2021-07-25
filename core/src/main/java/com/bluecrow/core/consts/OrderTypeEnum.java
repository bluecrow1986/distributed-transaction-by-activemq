package com.bluecrow.core.consts;

/**
 * @author BlueCrow
 * @Package com.bluecrow.core.consts
 * @Decription 事件类型枚举
 * @date 2021/7/24 20:25
 */
public enum OrderTypeEnum {

    INIT(0, "初始化"),
    SENT(1, "已发送"),
    RECEIVED(2, "已接收"),


    ;

    private Integer code;

    private String value;

    OrderTypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
