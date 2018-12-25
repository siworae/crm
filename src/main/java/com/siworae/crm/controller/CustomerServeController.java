package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.CustomerServe;
import com.siworae.crm.query.CustomerServeQuery;
import com.siworae.crm.service.CustomerServeService;
import com.siworae.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("customerServe")
public class CustomerServeController extends BaseController {

    @Autowired
    private CustomerServeService customerServeService;

    @RequestMapping("index/{state}")
    public String index(@PathVariable Integer state){
        if(state==1){
            return "customer_serve_create";
        }else if(state==2){
            return "customer_serve_assign";
        }else if(state==3){
            return "customer_serve_proce";
        }else if(state==4){
            return "customer_serve_feed_back";
        }else if(state==5){
            return "customer_serve_archive";
        }
        return "error";
    }


    @RequestMapping("saveOrUpdateCustomerServe")
    @ResponseBody
    public ResultInfo saveOrUpdateCustomerServe(CustomerServe customerServer, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        customerServeService.saveOrUpdateCustomerServe(customerServer, userId);
        return success("操作成功");
    }

    @RequestMapping("queryCustomerServesByParams")
    @ResponseBody
    public Map<String, Object> queryCustomerServesByParams(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            CustomerServeQuery query) {
        query.setPageNum(page);
        query.setPageSize(rows);
        return customerServeService.queryForPage(query);
    }

}
