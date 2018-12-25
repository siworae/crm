package com.siworae.crm.controller;


import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.CustomerLoss;
import com.siworae.crm.po.CustomerReprieve;
import com.siworae.crm.query.CustomerReprieveQuery;
import com.siworae.crm.service.CustomerLossService;
import com.siworae.crm.service.CustomerReprieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("customerReprieve")
public class CustomerReprieveController extends BaseController {

    @Autowired
    private CustomerLossService customerLossService;

    @Autowired
    private CustomerReprieveService customerReprieveService;

    @RequestMapping("index")
    public String index(Integer lossId, Model model){
        CustomerLoss customerLoss = customerLossService.queryById(lossId);
        model.addAttribute(customerLoss);
        return "customer_loss_reprieve";
    }

    @RequestMapping("saveOrUpdateCustomerReprieve")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerReprieve(CustomerReprieve customerReprieve){
        customerReprieveService.saveOrUpdateCustomerReprieve(customerReprieve);
        return success("操作成功");
    }

    @RequestMapping("delReprieve")
    @ResponseBody
    public ResultInfo delReprieve(Integer id){
        customerReprieveService.delete(id);
        return success("操作成功");
    }

    @RequestMapping("queryCustomerReprievesByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerReprievesByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerReprieveQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerReprieveService.queryForPage(query);
    }


}
