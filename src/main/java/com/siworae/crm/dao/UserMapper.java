package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseDao<User> {
    public User queryUserByName(String userName);
    public List<Map> queryCustomerManagers();
}