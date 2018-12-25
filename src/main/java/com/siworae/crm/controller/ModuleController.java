package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.Module;
import com.siworae.crm.query.ModuleQuery;
import com.siworae.crm.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @ClassName: ModuleController
 * @Date: 2018/12/24 10:00
 * @Author: siworae
 */
@Controller
@RequestMapping("module")
public class ModuleController extends BaseController {

    @Autowired
    private ModuleService moduleService;

    @RequestMapping("index")
    public String index(){
        return "module";
    }

    @RequestMapping("queryModulesByParams")
    @ResponseBody
    public Map<String, Object> queryModulesByParams(ModuleQuery query,
                                                    @RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer rows){
        query.setPageNum(page);
        query.setPageSize(rows);
        return moduleService.queryForPage(query);
    }

    /**
     * 增加弹框二级下拉框回显
     * @param grade
     * @return
     */
    @RequestMapping("queryByGrade")
    @ResponseBody
    public List<Map> queryByGrade(Integer grade){
        return moduleService.queryByGrade(grade);
    }

    /**
     * 保存和更新
     * @param module
     * @return
     */
    @RequestMapping("saveOrUpdateModule")
    @ResponseBody
    public ResultInfo saveOrUpdateModule(Module module){
        moduleService.saveOrUpdateModule(module);
        return success("操作成功");
    }

    /**
     * 删除模块
     * @param ids
     * @return
     */
    @RequestMapping("deleteModule")
    @ResponseBody
    public ResultInfo deleteModuleById(Integer[] ids){
        moduleService.deleteModuleById(ids);
        return success("操作成功");
    }
}
