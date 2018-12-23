package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.RoleMapper;
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
        Integer id = role.getId();
        role.setUpdateDate(new Date());
        if (null == id){
            //增加操作
            role.setCreateDate(new Date());
            role.setIsValid(1);
            AssertUtil.isTrue(roleMapper.save(role) < 1,"增加失败");
        }else {
            //更新操作
            AssertUtil.isTrue(roleMapper.update(role) < 1,"更新失败");
        }
    }

}
