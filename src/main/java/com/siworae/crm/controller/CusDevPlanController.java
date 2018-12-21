package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.dto.SaleChanceDto;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.CusDevPlan;
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

    /**
     * 跳转到客户开发机会详情界面，并回显数据
     * @param sid
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Integer sid, Model model){
        SaleChanceDto saleChanceDto = saleChanceService.queryById(sid);
        model.addAttribute("saleChance",saleChanceDto);
        return "cus_dev_plan_detail";
    }


    /**
     * 分页显示
     * @param query
     * @param rows
     * @param page
     * @return
     */
    @RequestMapping("queryCusDevPlansByParams")
    @ResponseBody
    public Map<String, Object> queryCusDevPlansByParams(CusDevPlanQuery query,
                                                        @RequestParam(defaultValue = "10") Integer rows,
                                                        @RequestParam(defaultValue = "1") Integer page ){
        query.setPageNum(page);
        query.setPageSize(rows);
        return cusDevPlanService.queryForPage(query);
    }

    /**
     * 保存和更新操作
     * @param cusDevPlan
     * @return
     */
    @RequestMapping("saveOrUpdateCusDevPlan")
    @ResponseBody
    public ResultInfo saveOrUpdate(CusDevPlan cusDevPlan){
        cusDevPlanService.saveOrUpdate(cusDevPlan);
        return success("操作成功");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("delectCusDevPlanBatch")
    @ResponseBody
    public ResultInfo delectCusDevPlanBatch(Integer[] ids){
        cusDevPlanService.delectCusDevPlanBatch(ids);
        return success("操作成功");
    }

    /**
     * 开发成功后更新状态
     * @param sid
     * @param devResult
     * @return
     */
    @RequestMapping("updateSaleChanceDevResult")
    @ResponseBody
    public ResultInfo updateSaleChanceDevResult(Integer sid, Integer devResult){
        saleChanceService.updateSaleChanceDevResult(sid,devResult);
        return success("操作成功");
    }
}
