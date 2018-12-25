package com.siworae.crm.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.UserMapper;
import com.siworae.crm.dao.UserRoleMapper;
import com.siworae.crm.dto.UserDto;
import com.siworae.crm.model.UserInfo;
import com.siworae.crm.po.User;
import com.siworae.crm.po.UserRole;
import com.siworae.crm.query.UserQuery;
import com.siworae.crm.utils.AssertUtil;
import com.siworae.crm.utils.Md5Util;
import com.siworae.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private UserRoleMapper userRoleMapper;
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

    /**
     * 分页查询用户
     * @param userQuery
     * @return
     */
    public Map<String, Object> queryUserRoleForPage(UserQuery userQuery){
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        List<UserDto> entities=userMapper.queryUserRoleByParams(userQuery);
        PageInfo<UserDto> pageInfo=new PageInfo<UserDto>(entities);
        List<UserDto> list = pageInfo.getList();
        //将后台查询到的角色id字符串转换成数组
        //把roleInStr 变成 list 存入roleIds
        for (UserDto userDao: list ) {
            String roleIdStr = userDao.getRoleIdStr();  //1,2,3
            if (!StringUtils.isBlank(roleIdStr)){
                String[] split = roleIdStr.split(",");
                List<Integer> roleIds = userDao.getRoleIds();
                for (int i = 0; i < split.length; i++) {
                    roleIds.add(Integer.valueOf(split[i]));
                }
            }
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    /**
     * 更新和添加操作
     * @param user
     */
    public void saveOrUpdateUser(User user,Integer[] roleIds){
        Integer userId = user.getId();

        user.setUpdateDate(new Date());

        //根据id判断操作是添加还是更新
        if (null == userId){
            //判断当前用户名是否重复
            AssertUtil.isTrue(userMapper.queryUserByName(user.getUserName()) != null,"用户名已存在");
            //增加操作
            user.setCreateDate(new Date());
            //设置默认密码123456
            user.setUserPwd(Md5Util.encode("123456"));
            user.setIsValid(1);
            AssertUtil.isTrue(userMapper.save(user) < 1,"保存失败");
        }else {
            //更新操作
            //判断用户是否修改了用户名
            String userName = userMapper.queryById(userId).getUserName();
            AssertUtil.isTrue(!userName.equals(user.getUserName()),"用户名不能修改");
            AssertUtil.isTrue(userMapper.update(user) < 1,"更新失败");
            //判断当前用户是否存在角色
            int total = userRoleMapper.queryCountByUserId(userId);
            if (total > 0){
                AssertUtil.isTrue(userRoleMapper.deleteByUserId(userId) < total,"删除用户角色失败");
            }
        }

        //增加用户角色
        List<UserRole> list = new ArrayList<>();
        for (Integer roleId:roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRole.setCreateDate(new Date());
            userRole.setUpdateDate(new Date());
            list.add(userRole);
        }
        AssertUtil.isTrue(userRoleMapper.saveBatch(list) < list.size(),"增加用户角色失败");
    }

    /**
     * 批量删除用户操作
     * @param ids
     */
    public void deleteUserBatch(Integer[] ids){
        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除用户!");
        //删除用户
        AssertUtil.isTrue(userMapper.deleteUserBatch(ids) < ids.length,"删除失败");

        //删角色
        for (Integer id:ids) {
            //判断当前用户是否拥有角色
            int total = userRoleMapper.queryCountByUserId(id);
            if (total > 0){
                AssertUtil.isTrue(userRoleMapper.deleteByUserId(id)<total,"删除角色失败");

            }
        }
    }

    /**
     * 查询当前用户所拥有的权限码
     * @param userId
     * @return
     */
    public List<String> queryAllaclVauleByUserId(Integer userId){
        return userMapper.queryAllaclVauleByUserId(userId);
    }
}
