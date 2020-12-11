package com.gyp.jedis.constant;

/**
 * Description:
 *
 * @author GYP
 * @version 1.0
 */
public enum RequestStatus {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),

    /**
     * 代表智库2.0的请求成功
     */
    SUCCESSZK2(1, "请求成功"),

    /**
     * 请求失败
     */
    ERROR(-1, "请求失败"),

    /**
     * 远程调用失败
     */
    FALL_BACK(-2, "服务暂不可用"),

    /**
     * 尚未登录
     */
    UN_LOGIN(-10000, "尚未登录");


    private int code;

    private String msg;

    RequestStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
