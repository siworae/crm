package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.model.UserInfo;
import com.siworae.crm.service.UserService;
import com.siworae.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @ClassName: UserController
 * @Date: 2018/12/17 19:01
 * @Author: siworae
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    /**
     * @Description: 用户登陆操作
     * @param： userName
     * @param： userPwd
     * @Return: ResultInfo
     * @Author: siworae
     * @Date: 2018/12/17 19:49
     */
    @RequestMapping("login")
    @ResponseBody
    public ResultInfo login(String userName, String userPwd){

        UserInfo userInfo = userService.login(userName, userPwd);
        return success("登陆成功",userInfo);
    }

    /**
     * @Description: 修改密码
     * @param： oldPassword
	 * @param： newPassword
	 * @param： confirmPassword
	 * @param： userId
     * @Return: com.siworae.crm.model.ResultInfo
     * @Author: siworae
     * @Date: 2018/12/18 15:12
     */
    @RequestMapping("updateUserPwd")
    @ResponseBody
    public ResultInfo updateUserPwd(String oldPassword, String newPassword, String confirmPassword, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        userService.updateUserPwd(oldPassword,newPassword,confirmPassword,userId);
        return success("更新成功");
    }

    /**
     * @Description: 查询客户经理
     * @Return: java.util.List<java.util.Map>
     * @Author: siworae
     * @Date: 2018/12/20 16:54
     */
    @RequestMapping("queryCustomerManagers")
    @ResponseBody
    public List<Map> queryCustomerManagers(){
        return userService.queryCustomerManagers();
    }
}
