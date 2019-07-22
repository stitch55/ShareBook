package com.project.sharebook.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//登陆拦截
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //在controller 处理之前进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查session 域中是否有name
        Object user  = request.getSession().getAttribute("user");
        System.out.println("对未登录用户进行拦截");
        if(user==null){
            request.getRequestDispatcher("/").forward(request,response);
           // response.sendRedirect("/");
            return false;
        }else {
            //已经登陆，放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
