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

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public final class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    public static Set<Class<?>> getClassSetByAnnotation(Set<Class<?>> classSet, Class annotation) {
        Set<Class<?>> res = new HashSet<>();
        for (Class<?> c : classSet) {
            Annotation a = c.getAnnotation(annotation);
            if (null != a) {
                res.add(c);
            }
        }
        return res;
    }

    public static Set<Method> getMethodSetByAnnotation(Class<?> clazz, Class annotation) {
        Set<Method> method = new HashSet<>();
        return null;
    }

    public static Class<?> loadClass(String className, boolean isInitialized) {
        return loadClass(getClassLoader(), className, isInitialized);
    }

    public static Class<?> loadClass(ClassLoader classLoader, String className, boolean isInitialized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitialized, classLoader);
        } catch (Exception e) {
            LOGGER.error("class {} not found", className, e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    public static Set<Class<?>> getClassSet(String packageName) {
        return getClassSet(getClassLoader(), packageName);
    }

    public static Set<Class<?>> getClassSet(ClassLoader classLoader, String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> urls = classLoader.getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (null != url) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String path = url.getPath();
                        doAddClass(classSet, packageName, URLDecoder.decode(path, "UTF-8"));
                    } else if ("jar".equals(protocol)) {
                    }
                }
            }

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        return classSet;
    }

    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static void doAddClass(Set<Class<?>> classSet, String packageName, String path) throws ClassNotFoundException {
        File file = new File(path);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".class") || pathname.isDirectory();
            }
        });

        for (File f : files) {
            if (f.isFile()) {
                String fileName = f.getName();
                int indexDot = fileName.indexOf(".");
                String className = packageName + "." + fileName.substring(0, indexDot);
                Class clazz = Class.forName(className);
                classSet.add(clazz);
            } else {
                String subPackageName = packageName + "." + f.getName();
                String subPath = path + "/" + f.getName();
                doAddClass(classSet, subPackageName, subPath);
            }
        }
    }
}

