package com.zss.personalspace.common;

/**
 * 枚举类
 */

public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    ERROR(404, "ERROR"),
    NEED_LOGIN(401, "NEED_LOGIN"),
    PARAM_ERROR(402, "PARAM_ERROR"),
    SAME_PASSWORD(100, "SAME_PASSWORD");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
