package com.gyp.jedis.exception;

import com.gyp.jedis.constant.CommonMessage;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Description: 自定义异常CHECK Handler
 *
 * @author GYP
 * @version 1.0
 */
public interface SystemExceptionHandler {

    /**
     * 校验是否为NULL.
     *
     * @param data 待校验对象
     */
    static void nullCheck(final Object data) {
        nullCheck(data, CommonMessage.DATA_ERROR);
    }

    /**
     * 校验是否为NULL.
     *
     * @param data       待校验对象
     * @param errMessage 错误信息
     */
    static void nullCheck(final Object data, final String errMessage) {
        flagCheck(Objects.isNull(data), errMessage);

        if (data instanceof String) {
            flagCheck(StringUtils.isEmpty(data), errMessage);
        } else if (data instanceof String[]) {
            flagCheck(((String[]) data).length == 0, errMessage);
        } else if (data instanceof List<?>) {
            flagCheck(CollectionUtils.isEmpty((List<?>) data), errMessage);
        } else if (data instanceof Map<?, ?>) {
            flagCheck(CollectionUtils.isEmpty((Map<?, ?>) data), errMessage);
        }
    }

    /**
     * 根据flag判断是否需要抛出异常.
     *
     * @param flag       标记状态
     * @param errMessage 错误信息
     */
    static void flagCheck(final boolean flag, final String errMessage) {
        if (flag) {
            throwException(errMessage);
        }
    }

    /**
     * 抛出异常信息.
     *
     * @param errMessage 错误信息
     */
    static void throwException(final String errMessage) {
        throw returnException(errMessage);
    }

    /**
     * 抛出异常信息.
     *
     * @param throwable 错误信息
     */
    static void throwException(final Throwable throwable) {
        throw returnException(throwable);
    }

    /**
     * 返回异常信息.
     *
     * @param errMessage 错误信息
     * @return Exception
     */
    static SystemException returnException(final String errMessage) {
        return new SystemException(errMessage);
    }

    /**
     * 返回异常信息.
     *
     * @param cause 错误信息
     * @return Exception
     */
    static SystemException returnException(final Throwable cause) {
        return new SystemException(cause);
    }
}
