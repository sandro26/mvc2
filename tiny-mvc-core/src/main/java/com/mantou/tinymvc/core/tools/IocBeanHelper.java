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
import com.mantou.tinymvc.core.utils.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IocBeanHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(IocBeanHelper.class);

    private static Map<Class<?>, Object> CONTROLLER_BEAN_MAP = new HashMap<>();

    static {
        addController();
    }

    private static void addController() {
        Set<Class<?>> controllerClass = ControllerHelper.getControllerClassSet();
        for (Class<?> clazz : controllerClass) {
            Object controller = ReflectionUtil.newInstance(clazz);
            CONTROLLER_BEAN_MAP.put(clazz, controller);
            LOGGER.info("instance controller {}", clazz.getName());
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return (T)CONTROLLER_BEAN_MAP.get(clazz);
    }
}
