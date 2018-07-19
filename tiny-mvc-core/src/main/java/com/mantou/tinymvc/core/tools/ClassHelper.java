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
 * <p/>
 * *
 */


package com.mantou.tinymvc.core.tools;

import com.mantou.tinymvc.core.annotation.Controller;
import com.mantou.tinymvc.core.utils.ClassUtil;

import java.util.Set;

public class ClassHelper {

    public static Set<Class<?>> getController(String packageName) {
        Set<Class<?>> classSet = ClassUtil.getClassSet(packageName);
        return ClassUtil.getClassSetByAnnotation(classSet, Controller.class);
    }
}
