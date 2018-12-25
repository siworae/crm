package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.CustomerOrderMapper;
import com.siworae.crm.po.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xlf on 2018/10/22.
 */
@Service
public class CustomerOrderService extends BaseService<CustomerOrder> {

    @Autowired
    private CustomerOrderMapper customerOrderMapper;
}
