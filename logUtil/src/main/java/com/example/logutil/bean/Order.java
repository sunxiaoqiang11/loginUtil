package com.example.logutil.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Order
implements Parcelable {
    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-10-27 10:25:05
     * updateBy : null
     * updateTime : null
     * remark : null
     * params : {}
     * orderNum : 60376550
     * id : 5
     * path : 一号线
     * start : 泰德大厦
     * end : 大连北站
     * price : 8
     * userName : 张三
     * userTel : 12345611
     * userId : 1
     * status : 1
     */

    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private ParamsBean params;
    private String orderNum;
    private int id;
    private String path;
    private String start;
    private String end;
    private int price;
    private String userName;
    private String userTel;
    private int userId;
    private int status;

    protected Order(Parcel in) {
        createTime = in.readString();
        orderNum = in.readString();
        id = in.readInt();
        path = in.readString();
        start = in.readString();
        end = in.readString();
        price = in.readInt();
        userName = in.readString();
        userTel = in.readString();
        userId = in.readInt();
        status = in.readInt();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createTime);
        dest.writeString(orderNum);
        dest.writeInt(id);
        dest.writeString(path);
        dest.writeString(start);
        dest.writeString(end);
        dest.writeInt(price);
        dest.writeString(userName);
        dest.writeString(userTel);
        dest.writeInt(userId);
        dest.writeInt(status);
    }

    public static class ParamsBean {
    }
}
