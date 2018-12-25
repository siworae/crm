package com.siworae.crm.controller;


import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.Customer;
import com.siworae.crm.query.CustomerQuery;
import com.siworae.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("index")
    public String index(){
        return "customer";
    }

    @RequestMapping("queryCustomersByParams")
    @ResponseBody
    public Map<String, Object> queryCustomersByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerService.queryForPage(query);
    }


    @RequestMapping("queryDataDicsByDicName")
    @ResponseBody
    public List<Map> queryDataDicsByDicName(String dicName){
        return customerService.queryCustomerLevel(dicName);
    }

    @RequestMapping("saveOrUpdateCustomer")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomer(Customer customer){
        customerService.saveOrUpdateCustomer(customer);
        return success("操作成功");
    }


    @RequestMapping("deleteCustomerBatch")
    @ResponseBody
    public ResultInfo deleteCustomerBatch(Integer[] ids){
        customerService.deleteBatch(ids);
        return success("操作成功");
    }

    @RequestMapping("addLossCustomers")
    @ResponseBody
    public ResultInfo addLossCustomers(){
        customerService.addLossCustomers();
        return success("操作成功");
    }




}
