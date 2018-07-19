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


package com.mantou.tinymvc.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigUtil {

    private static final String propertiesPath = "tiny-mvc.properties";

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtil.class);

    private static Properties properties = new Properties();

    static {

        try {
            properties.load(ConfigUtil.class.getClassLoader().getResourceAsStream(propertiesPath));
        } catch (IOException e) {
            LOGGER.error("load property file error !!!");
        }
        Enumeration e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String strKey = (String) e.nextElement();
            String strValue = properties.getProperty(strKey);
            LOGGER.info(" property key = {} : value = {}", strKey, strValue);
        }
    }

    public static String getProperty(String key) {
        if (properties != null) {
            return properties.getProperty(key);
        }
        return "";
    }

}
