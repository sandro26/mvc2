/**
======================
@author : mantou
@date : 2017/11/29
======================
Description:

======================
Major changes:

*/


package com.mantou.tinymvc.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mantou.tinymvc.request.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    public static Object newInstance(Class<?> clazz) {
        Object instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Object invokeMethod(Object object, Method method, Param param, HttpServletRequest req, HttpServletResponse resp) {
        try {
            return method.invoke(object, req, resp, param);
        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        return null;
    }
}
