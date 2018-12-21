package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.dto.SaleChanceDto;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.CusDevPlan;
import com.siworae.crm.po.SaleChance;
import com.siworae.crm.query.CusDevPlanQuery;
import com.siworae.crm.service.CusDevPlanService;
import com.siworae.crm.service.SaleChenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: crm
 * @ClassName: cusDevPlanController
 * @Date: 2018/12/20 21:49
 * @Author: siworae
 */
@RequestMapping("cusDevPlan")
@Controller
public class CusDevPlanController extends BaseController {

    @Autowired
    private SaleChenceService saleChanceService;

    @Autowired
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("index")
    public String index(Integer sid, Model model){
        SaleChanceDto saleChanceDto = saleChanceService.queryById(sid);
        model.addAttribute("saleChance",saleChanceDto);
        return "cus_dev_plan_detail";
    }


    @RequestMapping("queryCusDevPlansByParams")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery query,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        @RequestParam(defaultValue = "1") Integer page ){
        query.setPageNum(page);
        query.setPageSize(rows);
        return cusDevPlanService.queryForPage(query);
    }

    @RequestMapping("saveOrUpdateCusDevPlan")
    @ResponseBody
    public ResultInfo saveOrUpdate(CusDevPlan cusDevPlan){
        cusDevPlanService.saveOrUpdate(cusDevPlan);
        return success("操作成功");
    }

    @RequestMapping("delectCusDevPlanBatch")
    @ResponseBody
    public ResultInfo delectCusDevPlanBatch(Integer[] ids){
        cusDevPlanService.delectCusDevPlanBatch(ids);
        return success("操作成功");
    }
}
