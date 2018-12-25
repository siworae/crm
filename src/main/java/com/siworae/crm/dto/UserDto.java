package com.siworae.crm.dto;


import com.siworae.crm.po.User;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends User {

    private String roleName;
    private String roleIdStr;
    private List<Integer> roleIds = new ArrayList<>();
    List<String> roleNames = new ArrayList<>();// 存角色名称

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public String getRoleIdStr() {
        return roleIdStr;
    }

    public void setRoleIdStr(String roleIdStr) {
        this.roleIdStr = roleIdStr;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
