package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.CusDevPlanMapper;
import com.siworae.crm.dao.SaleChanceMapper;
import com.siworae.crm.dto.SaleChanceDto;
import com.siworae.crm.po.CusDevPlan;
import com.siworae.crm.utils.AssertUtil;
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

    @Autowired
    private SaleChanceMapper saleChanceMapper;

    /**
     * 保存和更新开发计划项
     * @param cusDevPlan
     */
    public void saveOrUpdate(CusDevPlan cusDevPlan){

        //补全参数
        cusDevPlan.setUpdateDate(new Date());
        if (null == cusDevPlan.getId()){
            //增加操作
            cusDevPlan.setCreateDate(new Date());
            cusDevPlan.setIsValid(1);
            AssertUtil.isTrue(cusDevPlanMapper.save(cusDevPlan) < 1,"增加失败");

            /**
             * 新增开发机会项时判断开发状态，如果是未开发则在新增开发机会项后将开发状态更改为开发中
             */
            Integer sid = cusDevPlan.getSaleChanceId();
            //通过id查询saleChance表
            SaleChanceDto saleChanceDto = saleChanceMapper.queryById(sid);
            if (saleChanceDto.getDevResult() == 0){
                //0=未开发，则将salechance表开发状态改为开发中
                AssertUtil.isTrue(saleChanceMapper.updateByParams(sid,1)<1, "操作失败");
            }
        }else {
            //更新操作
            AssertUtil.isTrue(cusDevPlanMapper.update(cusDevPlan) < 1,"更新失败");
        }
    }

    public void delectCusDevPlanBatch(Integer[] ids){
        AssertUtil.isTrue(cusDevPlanMapper.deleteBatch(ids) < 1, "删除失败");
    }

}
