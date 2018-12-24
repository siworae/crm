package com.siworae.crm.controller;

import com.siworae.crm.base.BaseController;
import com.siworae.crm.dao.PermissionMapper;
import com.siworae.crm.dto.ModuleDto;
import com.siworae.crm.model.ResultInfo;
import com.siworae.crm.po.Role;
import com.siworae.crm.query.RoleQuery;
import com.siworae.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @ClassName: RoleController
 * @Date: 2018/12/22 15:22
 * @Author: siworae
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index(){
        return "role";
    }

    /**
     * 用户管理角色下拉框数据
     * @return
     */
    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map> queryAllRoles(){
        return roleService.queryAllRoles();
    }

    /**
     * 分页查询
     * @param query
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("queryRolesByParams")
    @ResponseBody
    public Map<String, Object> queryRolesByParams(RoleQuery query,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer rows){
        query.setPageNum(page);
        query.setPageSize(rows);
        return roleService.queryForPage(query);
    }

    /**
     * 保存和更新
     * @param role
     */
    @RequestMapping("saveOrUpdateRole")
    @ResponseBody
    public ResultInfo saveOrUpdateRole(Role role){
        roleService.saveOrUpdateRole(role);
        return success("操作成功");
    }

    /**
     * 批量删除角色并删除角色所拥有的权限和拥有角色的用户中间表
     * @param ids
     * @return
     */
    @RequestMapping("deleteRole")
    @ResponseBody
    public ResultInfo deleteRole(Integer[] ids){
        roleService.deleteRole(ids);
        return success("删除成功");
    }

    /**
     * 根据角色id查询角色所拥有的全部权限
     * @param roleId
     * @return
     */
    @RequestMapping("queryPermissionByRoleId")
    @ResponseBody
    public List<ModuleDto> queryPermissionByRoleId(Integer roleId){
        return roleService.queryPermissionByRoleId(roleId);
    }

}
