package com.gyp.jedis.util.request;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Description:
 *
 * @author GYP
 */
public class RequestUtil {

    private static final String AJAX_FLAG = "XMLHttpRequest";

    private static final String AJAX_TRUE_FLAG = "X-Requested-With";

    /**
     * 获取当前Request
     *
     * @return 当前Request
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(requestAttributes)) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }


    /**
     * 判断当前请求是否是AJAX 请求
     *
     * @return true | false
     */
    public static boolean isAjaxRequest() {
        HttpServletRequest request;

        return (request = getRequest()) != null &&
                AJAX_FLAG.equals(request.getHeader(AJAX_TRUE_FLAG));
    }
}
