package com.gyp.jedis.constant;

/**
 * Description: 通用提示常量
 *
 * @author GYP
 * @version 1.0
 */
public interface CommonMessage {

    /**
     * 数据操作失败
     * 空的数据
     */
    String DATA_ERROR = "暂无数据，请刷新页面重试";

    /**
     * 未知错误
     */
    String UNKNOWN_MISTAKE = "未知错误，请稍后再试";


    String EXIST_USER_NAME = "用户名已被占用，请重新输入";

    String EXIST_NAME = "名称已被占用，请重新输入";
}

