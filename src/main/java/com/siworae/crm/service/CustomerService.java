package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.CustomerLossMapper;
import com.siworae.crm.dao.CustomerMapper;
import com.siworae.crm.po.Customer;
import com.siworae.crm.po.CustomerLoss;
import com.siworae.crm.utils.AssertUtil;
import com.siworae.crm.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends BaseService<Customer> {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerLossMapper customerLossMapper;

    /**
     * 添加流失客户
     * @return
     */
    public void addLossCustomers(){
        /***
         * 1. 查询所有流失客户
         * 2. 批量插入客户流失表
         * 3. 把查出的客户state状态变更为1
         * */
        List<Customer> customerList = customerMapper.queryLossCustomers();
        if(!CollectionUtils.isEmpty(customerList)){
            // 存流失客户列表
            List<CustomerLoss> customerLossList = new ArrayList<>();

            for(Customer customer : customerList){
                CustomerLoss customerLoss = new CustomerLoss();
                customerLoss.setCusNo(customer.getKhno());
                customerLoss.setCusName(customer.getName());
                customerLoss.setCusManager(customer.getCusManager());
                customerLoss.setState(0);// 预流失
                customerLoss.setIsValid(1);// 有效
                customerLoss.setCreateDate(new Date());
                customerLoss.setUpdateDate(new Date());
                customerLossList.add(customerLoss);
            }
            AssertUtil.isTrue(customerLossMapper.saveBatch(customerLossList)<customerLossList.size(),"操作成功");
            AssertUtil.isTrue(customerMapper.updateCustomerState(customerList)<customerList.size(), "操作成功");
        }
    }


    public void saveOrUpdateCustomer(Customer customer){
        // 参数校验省略....
        customer.setUpdateDate(new Date());
        Integer id = customer.getId();

        if(null==id){
            customer.setState(0);// 未流失
            customer.setIsValid(1);// 有限
            customer.setCreateDate(new Date());
            // 客户编号
            customer.setKhno(MathUtil.genereateKhCode());

            AssertUtil.isTrue(customerMapper.save(customer)<1, "操作成功");
        }else{
            AssertUtil.isTrue(customerMapper.update(customer)<1, "操作成功");
        }

    }

    public List<Map> queryCustomerLevel(String dicName){
        return customerMapper.queryCustomerLevel(dicName);
    }

    public List<Map> queryCustomerLevelNums(){
        return customerMapper.queryCustomerLevelNums();
    }
}
