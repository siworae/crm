package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.CusDevPlanMapper;
import com.siworae.crm.po.CusDevPlan;
import com.siworae.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: crm
 * @ClassName: CusDevPlanService
 * @Date: 2018/12/21 14:52
 * @Author: siworae
 */
@Service
public class CusDevPlanService extends BaseService<CusDevPlan> {

    @Autowired
    private CusDevPlanMapper cusDevPlanMapper;

    public void saveOrUpdate(CusDevPlan cusDevPlan){

        //补全参数
        cusDevPlan.setUpdateDate(new Date());
        if (null == cusDevPlan.getId()){
            //增加操作
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setIsValid(1);
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan) < 1,"增加失败");
        }else {
            //更新操作
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan) < 1,"更新失败");
        }
    }


}
