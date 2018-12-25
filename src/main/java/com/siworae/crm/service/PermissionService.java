package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.ModuleMapper;
import com.siworae.crm.dao.PermissionMapper;
import com.siworae.crm.po.Module;
import com.siworae.crm.po.Permission;
import com.siworae.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: crm
 * @ClassName: PermissionService
 * @Date: 2018/12/23 22:16
 * @Author: siworae
 */
@Service
public class PermissionService extends BaseService<Permission> {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * 角色授权
     * @param roleId
     * @param moduleIds
     */
    public void doGrant(Integer roleId,Integer[] moduleIds){
        AssertUtil.isTrue(null==roleId||roleId==0,"请选择角色!");
        //新增权限时先删除所有权限
        //判断当前角色是否有权限，如果有则删除
        int total = permissionMapper.queryCountByRoleId(roleId);
        if (total >0){
            AssertUtil.isTrue(permissionMapper.deleteByRoleId(roleId) <total , "角色权限删除失败");
        }
        if (null != moduleIds && moduleIds.length != 0){
            List<Permission> list = new ArrayList<>();
            for (Integer moduleId:moduleIds) {
                Permission permission = new Permission();
                permission.setRoleId(roleId);
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                permission.setModuleId(moduleId);
                //根据模块id获取模块权限码
                Module module = moduleMapper.queryById(moduleId);
                permission.setAclValue(module.getOptValue());

                list.add(permission);
            }
            permissionMapper.saveBatch(list);
        }
    }
}
