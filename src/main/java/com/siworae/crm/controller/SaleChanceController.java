package com.siworae.crm.controller;

import com.siworae.crm.annations.RequestPermission;
import com.siworae.crm.base.BaseController;
import com.siworae.crm.dto.SaleChanceDto;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.query.SaleChanceQuery;
import com.siworae.crm.service.SaleChenceService;
import com.siworae.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: crm
 * @ClassName: SaleChanceController
 * @Date: 2018/12/20 10:37
 * @Author: siworae
 */
@RequestMapping("saleChance")
@Controller
public class SaleChanceController extends BaseController {

    @Autowired
    private SaleChenceService saleChenceService;

    /**
     * 打开营销机会管理和客户机会开发界面
     * @param state
     * @return
     */
    @RequestMapping("index/{state}")
    public String SaleChance(@PathVariable Integer state){
        if (state == 0){
            return "sale_chance";
        }else if (state == 1){
            return "cus_dev_plan";
        }else {
            return "error";
        }
    }

    /**
     * 分页查询
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestPermission(aclValue = "101001")
    @RequestMapping("querySaleChancesByParams")
    @ResponseBody
    public Map<String, Object> querySaleChancesByParams(SaleChanceQuery query,
                                                        @RequestParam(defaultValue = "1")Integer page,
                                                        @RequestParam(defaultValue = "10")Integer rows){
        query.setPageNum(page);
        query.setPageSize(rows);
        return saleChenceService.queryForPage(query);
    }

    /**
     * 保存和更新操作
     * @param saleChance
     * @param request
     * @return
     */
    @RequestMapping("saveOrUpdateSaleChance")
    @ResponseBody
    public ResultInfo saveOrUpdateSaleChance(SaleChanceDto saleChance, HttpServletRequest request){
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        saleChenceService.saveOrUpdateSaleChance(saleChance, userId);
        if (saleChance.getId() == null) {
            return success("增加成功");
        }else {
            return success("更新成功");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping("deleteSaleChanceBatch")
    @ResponseBody
    public ResultInfo deleteBatch(Integer[] ids){
        saleChenceService.deleteBatch(ids);
        return success("删除成功");
    }
}
