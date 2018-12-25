package com.siworae.crm.controller;


import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.CustomerLoss;
import com.siworae.crm.query.CustomerLossQuery;
import com.siworae.crm.service.CustomerLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("customerLoss")
public class CustomerLossController extends BaseController {

    @Autowired
    private CustomerLossService customerLossService;

    @RequestMapping("index")
    public String index(){
        return "customer_loss";
    }

    @RequestMapping("queryCustomerLossByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerLossByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerLossQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerLossService.queryForPage(query);
    }

    @RequestMapping("updateCustomerLoss")
    @ResponseBody
    public ResultInfo updateCustomerLoss(CustomerLoss customerLoss) {
        customerLossService.update(customerLoss);
        return success("操作成功");
    }


}
