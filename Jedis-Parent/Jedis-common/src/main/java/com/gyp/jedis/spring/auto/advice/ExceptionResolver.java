package com.gyp.jedis.spring.auto.advice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyp.jedis.ResultCreator;
import com.gyp.jedis.exception.AuthException;
import com.gyp.jedis.exception.SystemException;
import com.gyp.jedis.model.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: 全局异常的统一处理
 * 约定返回的HTTP状态均为200, 即AJAX只需要在success回调里, 根据不同的code进行处理即可
 *
 * @author GYP
 * @version 1.0
 */
@Slf4j(topic = "exceptionLog")
@ControllerAdvice
@ResponseBody
public class ExceptionResolver {

    @Autowired
    private ObjectMapper mapper;

    @ExceptionHandler(AuthException.class)
    public ModelAndView handleAuthException(AuthException e,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        // ResultDto errDto = ResultCreator.unLogin();
        // return innerHandleException(e, ResultCreator.error("参数解析失败[没有传递JSON数据]"), request, response);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter out = response.getWriter()) {
            String res = mapper.writeValueAsString("has logout j_username and j_password");

            out.write(res);
            out.flush();
        } catch (IOException innerEx) {
            innerEx.printStackTrace();
        }
        return null;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ModelAndView handleHttpMessageNotReadableException(HttpMessageNotReadableException e,
                                                              HttpServletRequest request,
                                                              HttpServletResponse response) {
        log.error("参数解析失败[没有传递JSON数据]", e);
        return innerHandleException(e, ResultCreator.error("参数解析失败[没有传递JSON数据]"), request, response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                              HttpServletRequest request,
                                                              HttpServletResponse response) {
        log.error("参数验证失败", e);
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return innerHandleException(e, ResultCreator.error(message), request, response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationException(ConstraintViolationException e,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) {
        log.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        String message = violations.stream()
                .map(ConstraintViolation::getMessage)
                .sorted(String::compareTo)
                .collect(Collectors.joining(","));

        return innerHandleException(e, ResultCreator.error(message), request, response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView handleHttpRequestMethodNotSupportedException(Exception e,
                                                                     HttpServletRequest request,
                                                                     HttpServletResponse response) {
        log.error("不支持的请求方法", e);
        return innerHandleException(e, ResultCreator.error("不支持的请求方法"), request, response);
    }

    @ExceptionHandler(SystemException.class)
    public ModelAndView handleSystemException(SystemException e,
                                              HttpServletRequest request,
                                              HttpServletResponse response) {
        // 迭代尝试观察当前是否是多次嵌套抛出SystemException
        while (e.getCause() instanceof SystemException) {
            e = (SystemException) e.getCause();
        }

        log.error("业务异常", e);
        return innerHandleException(e, ResultCreator.error(e.getMessage()), request, response);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        log.error("服务器开小差了", e);
        return innerHandleException(e, ResultCreator.error("服务器开小差了"), request, response);
    }

    @SuppressWarnings("unchecked")
    private ModelAndView innerHandleException(Exception e, ResultDto errorDto, HttpServletRequest request, HttpServletResponse response) {
        // if (RequestUtil.isAjaxRequest()) {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try (PrintWriter out = response.getWriter()) {
            String res = mapper.writeValueAsString(errorDto);

            out.write(res);
            out.flush();
        } catch (IOException innerEx) {
            innerEx.printStackTrace();
        }
        return null;
        // } else {
        //     ModelAndView mv = new ModelAndView();
        //     mv.setViewName("error/500");
        //
        //     return mv;
        // }
    }
}
