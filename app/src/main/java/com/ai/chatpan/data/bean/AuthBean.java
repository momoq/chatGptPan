package com.ai.chatpan.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthBean implements Serializable {


    @SerializedName("userInfo")
    private UserInfoDTO userInfo;
    @SerializedName("token")
    private String token;

    public UserInfoDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDTO userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserInfoDTO implements Serializable {
        @SerializedName("userId")
        private String userId;
        @SerializedName("userName")
        private String userName;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("email")
        private String email;
        @SerializedName("phonenumber")
        private String phonenumber;
        @SerializedName("openId")
        private Object openId;
        @SerializedName("vipType")
        private Integer vipType;
        @SerializedName("blanceNum")
        private Integer blanceNum;
        @SerializedName("blanceDate")
        private String blanceDate;
        @SerializedName("invitationCode")
        private String invitationCode;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public Object getOpenId() {
            return openId;
        }

        public void setOpenId(Object openId) {
            this.openId = openId;
        }

        public Integer getVipType() {
            return vipType;
        }

        public void setVipType(Integer vipType) {
            this.vipType = vipType;
        }

        public Integer getBlanceNum() {
            return blanceNum;
        }

        public void setBlanceNum(Integer blanceNum) {
            this.blanceNum = blanceNum;
        }

        public String getBlanceDate() {
            return blanceDate;
        }

        public void setBlanceDate(String blanceDate) {
            this.blanceDate = blanceDate;
        }

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        @Override
        public String toString() {
            return "UserInfoDTO{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", email='" + email + '\'' +
                    ", phonenumber='" + phonenumber + '\'' +
                    ", openId=" + openId +
                    ", vipType=" + vipType +
                    ", blanceNum=" + blanceNum +
                    ", blanceDate='" + blanceDate + '\'' +
                    ", invitationCode='" + invitationCode + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AuthBean{" +
                "userInfo=" + userInfo +
                ", token='" + token + '\'' +
                '}';
    }
}
