package com.siworae.crm.interceptors;

import com.siworae.crm.service.UserService;
import com.siworae.crm.utils.AssertUtil;
import com.siworae.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: crm
 * @ClassName: LoginInterceptors
 * @Date: 2018/12/18 20:08
 * @Author: siworae
 */

public class LoginInterceptors extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //通过cookie获取用户id
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        AssertUtil.isLogin(null == userId || null == userService.queryById(userId),"用户未登录");
        return true;
    }
}
