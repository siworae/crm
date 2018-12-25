package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.ModuleMapper;
import com.siworae.crm.dao.PermissionMapper;
import com.siworae.crm.po.Module;
import com.siworae.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @program: crm
 * @ClassName: ModuleService
 * @Date: 2018/12/24 10:01
 * @Author: siworae
 */
@Service
public class ModuleService extends BaseService<Module> {

    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 增加弹框二级下拉框回显
     * @param grade
     * @return
     */
    public List<Map> queryByGrade(Integer grade){
        return moduleMapper.queryByGrade(grade);
    }

    /**
     * 保存和更新
     * @param module
     */
    public void saveOrUpdateModule(Module module){
        /**
         * 1.校验参数
         * 2.补全参数
         * 3.执行sql
         */

        checkParams(module);
        module.setUpdateDate(new Date());

        if (null == module.getId()){
            //增加
            module.setCreateDate(new Date());
            module.setIsValid((byte) 1);
            AssertUtil.isTrue(moduleMapper.save(module)<1,"保存失败");
        }
    }

    /**
     * 校验参数
     * @param module
     */
    private void checkParams(Module module) {
        //模块名称
        String moduleName = module.getModuleName();
        //权限码
        String optValue = module.getOptValue();
        //菜单层级
        Integer grade = module.getGrade();
        //上级菜单id
        Integer parentId = module.getParentId();


        //非空校验
        AssertUtil.isTrue(StringUtils.isBlank(moduleName),"模块名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(optValue),"权限码为空" );
        AssertUtil.isTrue(null == grade,"层级菜单为空" );
        AssertUtil.isTrue(null == parentId,"上级菜单为空" );
        //验证唯一性
        AssertUtil.isTrue(null != moduleMapper.queryBymoduleName(moduleName),"模块名已存在");
        AssertUtil.isTrue(null != moduleMapper.queryByOptValue(optValue),"权限码已存在");

        /**根据菜单层级确定位数*/
        //当前菜单该有的位数
        int num = (grade+1)*2;
        AssertUtil.isTrue(num != optValue.length(),"权限码位数不对，应为"+num+"位");

        //验证权限码格式
        //判断是否是根菜单
        if (grade != 0){
            /**根据菜单层级确定父级菜单是否正确*/
            //获取父模块
            Module parentModule = moduleMapper.queryById(parentId);
            //得到父模块所在层级
            Integer parentGrade = parentModule.getGrade();
            AssertUtil.isTrue(grade-parentGrade != 1,"菜单层级不正确");
            /**根据父级菜单确定权限码格式是否正确*/
            //得到父模块权限码
            String parentOptValue = parentModule.getOptValue();
            AssertUtil.isTrue(!optValue.startsWith(parentOptValue),"权限码格式不正确，格式为"+parentOptValue+"xx");
        }else {
            module.setParentId(null);
        }
    }

    /**
     * 删除模块
     * @param ids
     */
    public void deleteModuleById(Integer[] ids){
        for (Integer id:ids) {
            /**删除当前模块*/
            AssertUtil.isTrue(null != permissionMapper.queryBymoduleId(id),"模块Id:"+id+"已被使用，无法删除");
            AssertUtil.isTrue(moduleMapper.deleteModuleById(id) < 1,"删除失败");

            /**删除下级模块*/
            Module module = moduleMapper.queryById(id);
            if (module.getGrade()!=2){
                //不为第三级则需要删除下级菜单
                String optValue = module.getOptValue();
                List<Module> modules = moduleMapper.queryLikeOptValue(optValue);
//                System.out.println(modules);
                if (modules.size() !=0 && modules != null){
                    for (Module m:modules) {
                        AssertUtil.isTrue(null != permissionMapper.queryBymoduleId(m.getId()),"模块Id:"+m.getId()+"已被使用，无法删除");
                        AssertUtil.isTrue(moduleMapper.deleteModuleLikeOptValue(m.getOptValue())<1,"删除下级菜单失败");
                    }
                }
            }
        }

    }

}
