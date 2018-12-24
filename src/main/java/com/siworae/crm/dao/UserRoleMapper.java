package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.UserRole;

public interface UserRoleMapper extends BaseDao<UserRole> {
    int deleteByRoleId(Integer id);
    int queryCountByRoleId(Integer id);
    int deleteByUserId(Integer id);
    int queryCountByUserId(Integer id);
}