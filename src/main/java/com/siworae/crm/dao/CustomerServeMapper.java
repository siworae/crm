package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.CustomerServe;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerServeMapper extends BaseDao<CustomerServe> {
    public List<Map> queryServeType();
}