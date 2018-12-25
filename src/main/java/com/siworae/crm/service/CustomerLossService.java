package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.CustomerLossMapper;
import com.siworae.crm.po.CustomerLoss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xlf on 2018/10/23.
 */
@Service
public class CustomerLossService extends BaseService<CustomerLoss> {

    @Autowired
    private CustomerLossMapper customerLossMapper;
}
