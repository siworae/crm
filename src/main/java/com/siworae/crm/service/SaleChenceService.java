package com.siworae.crm.service;

import com.siworae.crm.base.BaseService;
import com.siworae.crm.dao.SaleChanceMapper;
import com.siworae.crm.dto.SaleChanceDto;
import com.siworae.crm.po.SaleChance;
import com.siworae.crm.po.User;
import com.siworae.crm.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: crm
 * @ClassName: SaleChenceService
 * @Date: 2018/12/20 12:49
 * @Author: siworae
 */
@Service
public class SaleChenceService extends BaseService<SaleChanceDto> {
    @Autowired
    private SaleChanceMapper saleChanceMapper;
    @Autowired
    private UserService userService;

    /**
     * 保存和修改
     * @param saleChance
     * @param userId
     */
    public void saveOrUpdateSaleChance(SaleChanceDto saleChance, Integer userId){
        /**
         * 校验参数
         * 设置默认参数
         */
        checkParams(saleChance);
        saleChance.setUpdateDate(new Date());
        if (saleChance.getId() == null) {
            //查询用户信息
            User user = userService.queryById(userId);
            saleChance.setCreateMan(user.getUserName());// 创建人
            saleChance.setDevResult(0);//未开发
            saleChance.setIsValid(1);//有效
            saleChance.setCreateDate(new Date());

            if (StringUtils.isBlank(saleChance.getAssignMan())){
                saleChance.setState(0);//未分配
            }else {
                saleChance.setState(1);//已分配
                saleChance.setAssignTime(new Date());// 分配时间
            }
            AssertUtil.isTrue(saleChanceMapper.save(saleChance) < 1,"增加失败");
        }else {
            AssertUtil.isTrue(saleChanceMapper.update(saleChance) < 1,"更新失败");
        }
    }

    /**
     * 校验参数
     * @param saleChance
     */
    public void checkParams(SaleChance saleChance){
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getCustomerName()),"客户名称为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkMan()),"联系人为空");
        AssertUtil.isTrue(StringUtils.isBlank(saleChance.getLinkPhone()),"联系电话为空");
    }

}
