package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.PermissionMapper;
import com.siworae.crm.dao.RoleMapper;
import com.siworae.crm.dao.UserRoleMapper;
import com.siworae.crm.dto.ModuleDto;
import com.siworae.crm.po.Role;
import com.siworae.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @ClassName: RoleService
 * @Date: 2018/12/22 15:19
 * @Author: siworae
 */
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 用户模块数据回显
     * @return
     */
    public List<Map> queryAllRoles(){
        return roleMapper.queryAllRoles();
    }

    /**
     * 保存和更新操作
     * @param role
     */
    public void saveOrUpdateRole(Role role){
        //得到前台传入的角色名
        String roleName = role.getRoleName();
        Integer id = role.getId();
        role.setUpdateDate(new Date());
        if (null == id){
            //增加操作
            //验证用户名的唯一性
            AssertUtil.isTrue(roleMapper.queryByName(roleName) != null,"角色名已存在");
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role) < 1,"增加失败");
        }else {
            //更新操作
            //判断是否更新了角色名
            if (!roleMapper.queryById(id).getRoleName().equals(roleName)){
                AssertUtil.isTrue(roleMapper.queryByName(roleName) != null,"角色名已存在");
            }
            AssertUtil.isTrue(roleMapper.update(role) < 1,"更新失败");
        }
    }

    /**
     * 根据roleid查询用户权限
     * @param roleId
     * @return
     */
    public List<ModuleDto> queryPermissionByRoleId(Integer roleId){
        return roleMapper.queryPermissionByRoleId(roleId);
    }

    /**
     * 批量删除角色并删除角色所拥有的权限
     * @param ids
     */
    public void deleteRole(Integer[] ids) {
        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除记录!");
        //删除角色queryCountByRoleId
        AssertUtil.isTrue(roleMapper.deleteBatch(ids) < ids.length,"角色删除失败");
        for (Integer roleId:ids) {
            //查询角色是否拥有权限
            int total1 = permissionMapper.queryCountByRoleId(roleId);
            if ( total1 > 0){
                //删除角色所拥有的权限
                AssertUtil.isTrue(permissionMapper.deleteByRoleId(roleId) < total1,"角色权限删除失败");
            }
            //查询该角色是否被用户拥有
            int total2 = userRoleMapper.queryCountByRoleId(roleId);
            if (total2 > 0){
                //删除拥有角色的用户中间表
                AssertUtil.isTrue(userRoleMapper.deleteByRoleId(roleId) < total2,"角色用户删除失败");
            }
        }
    }

}
