package com.example.logutil.bean;

public class Deliver {
    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 1
     * userId : 2
     * postName : JAVA 开发工程师
     * companyName : 新型技术公司
     * money : 5000
     * satrTime : 2020-11-04
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private ParamsBean params;
    private int id;
    private int userId;
    private String postName;
    private String companyName;
    private String money;
    private String satrTime;

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getSatrTime() {
        return satrTime;
    }

    public void setSatrTime(String satrTime) {
        this.satrTime = satrTime;
    }

    public static class ParamsBean {
    }
}
