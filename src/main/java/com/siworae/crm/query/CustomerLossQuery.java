package com.siworae.crm.query;


import com.siworae.crm.base.BaseQuery;


/**
 * Created by xlf on 2018/10/23.
 */
public class CustomerLossQuery extends BaseQuery {

    private String cusNo;

    private String cusName;

    private String createDate;

    public String getCusNo() {
        return cusNo;
    }

    public void setCusNo(String cusNo) {
        this.cusNo = cusNo;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
