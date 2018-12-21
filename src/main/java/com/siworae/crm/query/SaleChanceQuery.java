package com.siworae.crm.query;

import com.siworae.crm.base.BaseQuery;

/**
 * @program: crm
 * @ClassName: SaleChanceQuery
 * @Date: 2018/12/20 12:40
 * @Author: siworae
 */
public class SaleChanceQuery extends BaseQuery {

    private String customerName;
    private Integer state;
    private Integer devResult;
    private String createDate;


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getDevResult() {
        return devResult;
    }

    public void setDevResult(Integer devResult) {
        this.devResult = devResult;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
