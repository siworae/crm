package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: crm
 * @ClassName: PermissionController
 * @Date: 2018/12/23 22:18
 * @Author: siworae
 */
@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;
    /**
     * 角色授权
     * @param roleId
     * @param moduleIds
     * @return
     */
    @RequestMapping("doGrant")
    @ResponseBody
    public ResultInfo doGrant(Integer roleId, Integer[] moduleIds){
        System.out.println("roleId:"+roleId+"----->"+"moduleIds:"+moduleIds);
        permissionService.doGrant(roleId, moduleIds);
        return success("授权成功");
    }
}
