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

import com.mantou.tinymvc.core.annotation.Controller;
import com.mantou.tinymvc.core.utils.ClassUtil;

public class HelpLoader {

    public static void init() {

        Class<?> [] classes = {Controller.class,};
        for (Class<?> clazz : classes) {
            ClassUtil.loadClass(clazz.getName(), true);
        }

    }
}
