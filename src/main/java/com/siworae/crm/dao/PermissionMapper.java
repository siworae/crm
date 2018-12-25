package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.Permission;

public interface PermissionMapper extends BaseDao<Permission> {
    int deleteByRoleId(Integer id);
    int queryCountByRoleId(Integer id);
    Permission queryBymoduleId(Integer moduleId);
}