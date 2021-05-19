package com.example.logutil.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Post implements Parcelable {
    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * id : 1
     * companyId : 3
     * contacts : 张先生
     * name : 软件开发
     * obligation : 负责软件的设计开发测试以及上线
     * address : 大连市万达广场
     * salary : 5000
     * professionId : 1
     * company : 虎鱼科技
     * need : 工作经验1-2年
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private ParamsBean params;
    private int id;
    private String companyId;
    private String contacts;
    private String name;
    private String obligation;
    private String address;
    private String salary;
    private String professionId;
    private String company;
    private String need;

    protected Post(Parcel in) {
        id = in.readInt();
        companyId = in.readString();
        contacts = in.readString();
        name = in.readString();
        obligation = in.readString();
        address = in.readString();
        salary = in.readString();
        professionId = in.readString();
        company = in.readString();
        need = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObligation() {
        return obligation;
    }

    public void setObligation(String obligation) {
        this.obligation = obligation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(companyId);
        dest.writeString(contacts);
        dest.writeString(name);
        dest.writeString(obligation);
        dest.writeString(address);
        dest.writeString(salary);
        dest.writeString(professionId);
        dest.writeString(company);
        dest.writeString(need);
    }

    public static class ParamsBean {
    }
}
