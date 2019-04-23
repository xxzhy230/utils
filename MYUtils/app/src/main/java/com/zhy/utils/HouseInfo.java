package com.zhy.utils;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;


@DatabaseTable(tableName = "houseTable", daoClass = HouseInfoDao.class)
public class HouseInfo implements Serializable {

    private static final long serialVersionUID = -6716919754718380845L;

    //主键id
    @DatabaseField(generatedId = true)
    private int id;

    //父设备ID 父设备ID为空表示此设备为主设备
    @DatabaseField
    private String pdid;

    // 产品ID
    @DatabaseField
    private String pid;

    // 产品ID
    @DatabaseField
    private String did;


    // 房间名称
    @DatabaseField
    private String houseName;

    // 用户id
    @DatabaseField
    private String userId;
    // 是否使用
    @DatabaseField
    private int state;// 1 使用  0 未使用

    private boolean isSeclet;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isSeclet() {
        return isSeclet;
    }

    public void setSeclet(boolean seclet) {
        isSeclet = seclet;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPdid() {
        return pdid;
    }

    public void setPdid(String pdid) {
        this.pdid = pdid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }


    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public HouseInfo() {
        super();
    }


}
