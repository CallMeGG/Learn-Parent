package com.gyp.jedis.api.system;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Title: ApplicationUtil
 * Description: ApplicationUtil
 *
 * @author GYP
 * @version 1.0
 */
@Component
public class ApplicationUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return ApplicationUtil.applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtil.applicationContext = applicationContext;
    }

    /**
     * 通过name获取 Bean.
     *
     * @param name name
     * @return Bean Instance
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过clazz获取 Bean.
     *
     * @param clazz clazz
     * @return Bean Instance
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }


    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  name
     * @param clazz Clazz
     * @param <T>   泛型
     * @return Bean Instance
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 通过key获取 配置文件.
     *
     * @param key name
     * @return Bean Instance
     */
    public static String getProperty(String key) {
        return getApplicationContext().getEnvironment().getProperty(key);
    }
}
