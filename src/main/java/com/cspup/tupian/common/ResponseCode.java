package com.cspup.tupian.common;

/**
 * @author csp
 * @date 2022/3/13 15:33
 * @description
 */
public enum ResponseCode {

    /**
     * SUCCESS 成功
     * ERROR 错误
     * UNLOGIN 未登录
     */
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"ERROR"),
    UNLOGIN(401,"UNLOGIN");
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