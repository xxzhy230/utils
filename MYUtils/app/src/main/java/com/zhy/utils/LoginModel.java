package com.zhy.utils;

import android.view.View;

import com.google.gson.Gson;
import com.yqjr.utils.flyBanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginModel {



    /**
     * state : 1
     * msg :
     * body : {"token":"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODg4ODg4ODg4OCIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE1NTU1Njg0NDR9.nop5JKgI_waYuiteTJ1o1dE6TjNGDt9IqLi59xxEl0y6A9gQ6_BDP9FU-YKad7ue7VSUOjhBlRsweZSJP9oBAg","userId":500,"userName":"车贷小助手","phoneNo":"18888888888","duty":"经销商销售顾问","dealerName":"虚拟经销商(互联网事业部)","socialCode":"87654321-1","socialExpDate":"","dealerAddr":"","dealerId":1008990635,"npType":"3"}
     */

    private String state;
    private String msg;
    private BodyBean body;




    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * token : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxODg4ODg4ODg4OCIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE1NTU1Njg0NDR9.nop5JKgI_waYuiteTJ1o1dE6TjNGDt9IqLi59xxEl0y6A9gQ6_BDP9FU-YKad7ue7VSUOjhBlRsweZSJP9oBAg
         * userId : 500
         * userName : 车贷小助手
         * phoneNo : 18888888888
         * duty : 经销商销售顾问
         * dealerName : 虚拟经销商(互联网事业部)
         * socialCode : 87654321-1
         * socialExpDate :
         * dealerAddr :
         * dealerId : 1008990635
         * npType : 3
         */

        private String token;
        private int userId;
        private String userName;
        private String phoneNo;
        private String duty;
        private String dealerName;
        private String socialCode;
        private String socialExpDate;
        private String dealerAddr;
        private int dealerId;
        private String npType;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getDuty() {
            return duty;
        }

        public void setDuty(String duty) {
            this.duty = duty;
        }

        public String getDealerName() {
            return dealerName;
        }

        public void setDealerName(String dealerName) {
            this.dealerName = dealerName;
        }

        public String getSocialCode() {
            return socialCode;
        }

        public void setSocialCode(String socialCode) {
            this.socialCode = socialCode;
        }

        public String getSocialExpDate() {
            return socialExpDate;
        }

        public void setSocialExpDate(String socialExpDate) {
            this.socialExpDate = socialExpDate;
        }

        public String getDealerAddr() {
            return dealerAddr;
        }

        public void setDealerAddr(String dealerAddr) {
            this.dealerAddr = dealerAddr;
        }

        public int getDealerId() {
            return dealerId;
        }

        public void setDealerId(int dealerId) {
            this.dealerId = dealerId;
        }

        public String getNpType() {
            return npType;
        }

        public void setNpType(String npType) {
            this.npType = npType;
        }
    }
}
