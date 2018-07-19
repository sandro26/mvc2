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


package com.mantou.tinymvc.controller;


import com.mantou.tinymvc.dto.DemoData;
import com.mantou.tinymvc.core.annotation.Action;
import com.mantou.tinymvc.core.annotation.Controller;
import com.mantou.tinymvc.request.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DemoController2 {

    @Action(method = "GET", path = "/")
    public Object getMethod(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Param param) {
        DemoData demoData = new DemoData();
        demoData.setAge(10);
        demoData.setName("kuma");
        return demoData;
    }

    @Action(method = "POST", path = "/demo/post2")
    public Object demoMethonPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Param param) {
        return null;
    }
}
