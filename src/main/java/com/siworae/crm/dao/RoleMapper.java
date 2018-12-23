package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper extends BaseDao<Role> {

    List<Map> queryAllRoles();
}