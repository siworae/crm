package com.siworae.crm.dto;

import com.siworae.crm.po.SaleChance;

/**
 * @program: crm
 * @ClassName: SaleChanceDto
 * @Date: 2018/12/21 10:33
 * @Author: siworae
 */
public class SaleChanceDto extends SaleChance {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
