package com.bluecrow.core.consts;

/**
 * @author BlueCrow
 * @Package com.bluecrow.core.consts
 * @Decription 同步标识枚举
 * @date 2021/7/24 20:25
 */
public enum SyncFlagEnum {

    SYNC(1, "同步"),
    ASYNC(2, "异步"),


    ;

    private Integer code;

    private String value;

    SyncFlagEnum(Integer code, String value) {
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
