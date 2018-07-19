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


package com.mantou.tinymvc.servlet;

import com.alibaba.fastjson.JSON;
import com.mantou.tinymvc.core.tools.ControllerHelper;
import com.mantou.tinymvc.core.tools.HelpLoader;
import com.mantou.tinymvc.core.tools.IocBeanHelper;
import com.mantou.tinymvc.core.utils.ReflectionUtil;
import com.mantou.tinymvc.request.Handler;
import com.mantou.tinymvc.request.Param;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("----------------init----------------");
        HelpLoader.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toUpperCase();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (null != handler) {
            Class<?> clazz = handler.getControllerClass();
            Method method = handler.getMethod();
            Object controller = IocBeanHelper.getBean(clazz);
            Map<String, Object> paramMap = new HashMap<>();

            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String param = paramNames.nextElement();
                String value = req.getParameter(param);
                paramMap.put(param, value);
            }
            Param param = new Param();
            param.setParam(paramMap);
            Object res = ReflectionUtil.invokeMethod(controller, method, param, req, resp);
            if (null != res) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter printWriter = resp.getWriter();
                String json = JSON.toJSONString(res);
                printWriter.write(json);
                printWriter.flush();
                printWriter.close();
            }
        } else {
            return;
        }
    }
}
