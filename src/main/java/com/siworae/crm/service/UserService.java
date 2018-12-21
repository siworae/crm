package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.UserMapper;
import com.siworae.crm.model.UserInfo;
import com.siworae.crm.po.User;
import com.siworae.crm.utils.AssertUtil;
import com.siworae.crm.utils.Md5Util;
import com.siworae.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: crm
 * @ClassName: UserService
 * @Date: 2018/12/17 19:55
 * @Author: siworae
 */
@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;
    /**
     * @Description: 登陆操作
     * @param： userName
	 * @param： userPwd
     * @Return: void
     * @Author: siworae
     * @Date: 2018/12/17 20:10
     */
    public UserInfo login(String userName, String userPwd){
        //判断用户名和密码
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名为空");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码为空");

        User user = userMapper.queryUserByName(userName);

        AssertUtil.isTrue(null == user,"用户不存在或已注销");
        AssertUtil.isTrue(! Md5Util.encode(userPwd).equals(user.getUserPwd()),"用户密码不正确");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userInfo.setUserName(user.getUserName());
        userInfo.setRealName(user.getTrueName());
        return userInfo;
    }
    /**
     * @Description: 修改密码
     * @param： newPassword
     * @param： userId
     * @Return: com.siworae.crm.model.ResultInfo
     * @Author: siworae
     * @Date: 2018/12/18 13:01
     */
    public void updateUserPwd(String oldPassword, String newPassword, String confirmPassword, Integer userId){
        //密码校验
        checkUserParams(oldPassword,newPassword,confirmPassword);
        //通过userId查询用户信息
        User user1 = userMapper.queryById(userId);
        AssertUtil.isTrue(user1 == null, "用户不存在");
        AssertUtil.isTrue(!Md5Util.encode(oldPassword).equals(user1.getUserPwd()),"原密码不正确");
        User user = new User();
        user.setId(userId);
        user.setUserPwd(Md5Util.encode(newPassword));
        AssertUtil.isTrue(userMapper.update(user)<1,"更新失败");
    }

    /**
     * @Description: 校验参数
     * @param： oldPassword
     * @param： newPassword
     * @param： confirmPassword
     * @Return: void
     * @Author: siworae
     * @Date: 2018/12/18 15:03
     */
    private void checkUserParams(String oldPassword, String newPassword, String confirmPassword) {
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码为空");
        AssertUtil.isTrue(StringUtils.isBlank(confirmPassword),"确定密码为空");
        AssertUtil.isTrue(!confirmPassword.equals(newPassword),"密码不一致，请重新输入");
    }

    /**
     * @Description: 查询客户经理
     * @Return: java.util.List
     * @Author: siworae
     * @Date: 2018/12/20 16:55
     */
    public List queryCustomerManagers(){
        return userMapper.queryCustomerManagers();
    }

}
