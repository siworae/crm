package com.siworae.crm.dao;

import com.siworae.crm.base.BaseDao;
import com.siworae.crm.po.Module;
import com.siworae.crm.query.ModuleQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ModuleMapper extends BaseDao<Module> {
    List<Module> queryByParams(ModuleQuery moduleQuery);
    List<Map> queryByGrade(Integer id);
}