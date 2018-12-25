package com.siworae.crm.service;


import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.CustomerReprieveMapper;
import com.siworae.crm.po.CustomerReprieve;
import com.siworae.crm.utils.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by xlf on 2018/10/23.
 */
@Service
public class CustomerReprieveService extends BaseService<CustomerReprieve> {

    @Autowired
    private CustomerReprieveMapper customerReprieveMapper;

    public void saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve){
        customerReprieve.setUpdateDate(new Date());

        Integer id = customerReprieve.getId();

        if(null == id){
            customerReprieve.setIsValid(1);
            customerReprieve.setCreateDate(new Date());
            AssertUtil.isTrue(customerReprieveMapper.save(customerReprieve)<1, "操作成功");
        }else{
            AssertUtil.isTrue(customerReprieveMapper.update(customerReprieve)<1, "操作成功");
        }
    }
}












