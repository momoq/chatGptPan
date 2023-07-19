package com.ai.chatpan.data.bean;

public class AuthRequest {

    private String userName;
    private String valismsCode;
    private String code;

    public AuthRequest(String userName, String valismsCode, String code) {
        this.userName = userName;
        this.valismsCode = valismsCode;
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getValismsCode() {
        return valismsCode;
    }

    public void setValismsCode(String valismsCode) {
        this.valismsCode = valismsCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
