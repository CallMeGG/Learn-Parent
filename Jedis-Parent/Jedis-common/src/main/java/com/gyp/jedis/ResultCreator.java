package com.gyp.jedis;


import com.gyp.jedis.constant.RequestStatus;
import com.gyp.jedis.model.dto.ResultDto;

/**
 * Description: 通讯JSON数据封装类Creator
 *
 * @author GYP
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class ResultCreator {
    /**
     * 成功
     *
     * @return ResultDto
     */
    public static ResultDto success() {
        return getInstance(RequestStatus.SUCCESS);
    }

    /**
     * 成功
     *
     * @param resultData 结果集
     * @return ResultDto
     */
    public static <T> ResultDto<T> success(T resultData) {
        return new ResultDto(RequestStatus.SUCCESS, resultData);
    }

    /**
     * 智库2.0task的成功不一样
     *
     * @param resultData
     * @param <T>
     * @return
     */
    public static <T> ResultDto<T> successZK2(T resultData) {
        return new ResultDto(RequestStatus.SUCCESSZK2, resultData);
    }

    /**
     * 失败
     *
     * @return ResultDto
     */
    public static ResultDto error() {
        return getInstance(RequestStatus.ERROR);
    }

    /**
     * 失败
     *
     * @param message 错误提示信息
     * @return ResultDto
     */
    public static ResultDto error(String message) {
        return getInstance(RequestStatus.ERROR.getCode(), message);
    }

    /**
     * fallback
     *
     * @return ResultDto
     */
    public static ResultDto fallback() {
        return getInstance(RequestStatus.FALL_BACK);
    }

    /**
     * fallback
     *
     * @param message 错误提示信息
     * @return ResultDto
     */
    public static ResultDto fallback(String message) {
        return getInstance(RequestStatus.FALL_BACK.getCode(), message);
    }

    /**
     * unLogin
     *
     * @return ResultDto
     */
    public static ResultDto unLogin() {
        return getInstance(RequestStatus.UN_LOGIN);
    }

    /**
     * unLogin
     *
     * @param message 尚未登录提示信息
     * @return ResultDto
     */
    public static ResultDto unLogin(String message) {
        return getInstance(RequestStatus.UN_LOGIN.getCode(), message);
    }


    private static ResultDto getInstance(RequestStatus requestStatus) {
        return new ResultDto(requestStatus.getCode(), requestStatus.getMsg());
    }

    private static ResultDto getInstance(Integer code, String message) {
        return new ResultDto(code, message);
    }

}
