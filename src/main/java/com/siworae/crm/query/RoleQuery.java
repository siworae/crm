package com.siworae.crm.query;

import com.siworae.crm.base.BaseQuery;

/**
 * @program: crm
 * @ClassName: RoleQuery
 * @Date: 2018/12/22 20:00
 * @Author: siworae
 */
public class RoleQuery extends BaseQuery {
    private String roleName;
    private String createDate;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
