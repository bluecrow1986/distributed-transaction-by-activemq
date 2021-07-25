package com.bluecrow.core.consts;

/**
 * @author BlueCrow
 * @Package com.bluecrow.core.consts
 * @Decription
 * @date 2021/7/25 13:29
 */
public enum PayStageEnum {

    WAITING_PAY(1, "待支付"),
    PAY(2, "支付中"),
    PAID(3, "已支付"),

    ;

    private Integer code;

    private String value;

    PayStageEnum(Integer code, String value) {
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
