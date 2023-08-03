package com.ai.chatpan.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AuthBean implements Serializable {


    @SerializedName("userInfo")
    private UserInfoDTO userInfo;
    @SerializedName("wxInfo")
    private WxInfoDTO wxInfo;
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
    public static class WxInfoDTO implements Serializable {


        @SerializedName("openid")
        private String openid;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("sex")
        private Integer sex;
        @SerializedName("language")
        private String language;
        @SerializedName("city")
        private String city;
        @SerializedName("province")
        private String province;
        @SerializedName("country")
        private String country;
        @SerializedName("headimgurl")
        private String headimgurl;
        @SerializedName("unionid")
        private String unionid;
        @SerializedName("privilege")
        private List<?> privilege;



        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(String headimgurl) {
            this.headimgurl = headimgurl;
        }

        public String getUnionid() {
            return unionid;
        }

        public void setUnionid(String unionid) {
            this.unionid = unionid;
        }

        public List<?> getPrivilege() {
            return privilege;
        }

        public void setPrivilege(List<?> privilege) {
            this.privilege = privilege;
        }

        @Override
        public String toString() {
            return "WxInfoDTO{" +
                    "openid='" + openid + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", sex=" + sex +
                    ", language='" + language + '\'' +
                    ", city='" + city + '\'' +
                    ", province='" + province + '\'' +
                    ", country='" + country + '\'' +
                    ", headimgurl='" + headimgurl + '\'' +
                    ", unionid='" + unionid + '\'' +
                    ", privilege=" + privilege +
                    '}';
        }
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
