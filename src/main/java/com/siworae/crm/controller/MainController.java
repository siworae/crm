package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.po.User;
import com.siworae.crm.service.UserService;
import com.siworae.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: crm
 * @ClassName: MainController
 * @Date: 2018/12/18 17:58
 * @Author: siworae
 */
@Controller
public class MainController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 打开首页
     * @param request
     * @return
     */
    @RequestMapping("main")
    public String main(HttpServletRequest request){
        /** 从cookie中获取登陆用户id，通过id查询登陆用户信息 */
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        User user = userService.queryById(userId);
        request.setAttribute("user",user);
        return "main";
    }
}
