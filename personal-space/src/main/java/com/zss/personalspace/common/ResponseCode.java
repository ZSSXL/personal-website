package com.zss.personalspace.common;

/**
 * 枚举类
 */

public enum ResponseCode {

    SUCCESS(200, "SUCCESS"),
    ERROR(400, "ERROR"),
    NEED_LOGIN(402, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(403, "ILLEGAL_ARGUMENT");

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
