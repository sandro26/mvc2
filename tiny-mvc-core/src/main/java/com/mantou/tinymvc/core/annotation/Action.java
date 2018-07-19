/**
======================
@author : mantou
@date : 2017/11/29
======================
Description:

======================
Major changes:

*/


package com.mantou.tinymvc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Action {

    String path() default "";

    String method() default "GET";

}
