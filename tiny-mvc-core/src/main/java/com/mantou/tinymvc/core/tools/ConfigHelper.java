/**
 * ======================
 *
 * @author : mantou
 * @date : 2018/1/11
 * ======================
 * Description:
 * <p/>
 * ======================
 * Major changes:
 */


package com.mantou.tinymvc.core.tools;

import com.mantou.tinymvc.core.utils.ConfigUtil;

public class ConfigHelper {

    private static final String PACKAGE_PATH_PROPERTIES_KEY = "package.path";

    public static String getControllerPackagePath() {
        return ConfigUtil.getProperty(PACKAGE_PATH_PROPERTIES_KEY);
    }
}
