/**
 * ======================
 *
 * @author : mantou
 * @date : 2018/1/10
 * ======================
 * Description:
 * <p/>
 * ======================
 * Major changes:
 */


package com.mantou.tinymvc.core.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mantou.tinymvc.core.annotation.Action;
import com.mantou.tinymvc.request.Handler;
import com.mantou.tinymvc.request.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ControllerHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHelper.class);

    private static final Map<Request, Handler> requestMap = new HashMap<>();

    private static Set<Class<?>> controllerClassSet;

    static {
        LOGGER.info("begin init controllerHelper");
        controllerClassSet = ClassHelper.getController(getPackagePath());
        for (Class<?> clazz : controllerClassSet) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Action.class)) {
                    Action action = method.getAnnotation(Action.class);
                    Request request = buildRequest(action.method(), action.path());
                    Handler handler = buildHandler(clazz, method);
                    requestMap.put(request, handler);
                    LOGGER.info("load Action {}", request.toString());
                }
            }
        }
    }

    public static Set<Class<?>> getControllerClassSet() {
        return controllerClassSet;
    }

    private static Request buildRequest(String method, String path) {
        return new Request(method, path);
    }

    private static Handler buildHandler(Class<?> clazz, Method method) {
        return new Handler(clazz, method);
    }

    private static String getPackagePath() {
        return ConfigHelper.getControllerPackagePath();
    }

    public static Map<Request, Handler> getRequestMap() {
        return requestMap;
    }

    public static Handler getHandler(String method, String path) {
        Request request = buildRequest(method, path);
        return requestMap.get(request);
    }
}
