package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerMapper extends BaseDao<Customer> {

    public List<Map> queryCustomerLevel(String dicName);

    public List<Customer> queryLossCustomers();
    public Integer updateCustomerState(List<Customer> customers);

    public List<Map> queryCustomerLevelNums();


}