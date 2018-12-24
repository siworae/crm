package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.ModuleMapper;
import com.siworae.crm.po.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Map> queryByGrade(Integer grade){
        return moduleMapper.queryByGrade(grade);
    }

}
