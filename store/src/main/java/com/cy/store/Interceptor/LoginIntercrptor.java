package com.cy.store.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginIntercrptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据，如果有则放行，如果没有就重定向到登录页面
     * @param request 请求对象
     * @param response 相应对象
     * @param handler 处理器(url+Controller:映射)
     * @return 返回值为true,表示放行当前请求，如果为false则表示拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 根据session中的值来动态确定是否放行
        Object obj = request.getSession().getAttribute("uid");
        if(obj == null){ // 说明用户没有登录过系统，则重定向到login.html
            response.sendRedirect("/web/login.html");
            // 结束后续调用
            return false;
        }
        // 有数据则放行
        return true;
    }
}
