package com.ai.chatpan.data.bean;

public class SendSMSCodeRequest {

    private String userName;
    private String passWord;
    private String conPassWord;
    private String valismsCode;
    private String  code;

    public SendSMSCodeRequest(String userName, String passWord, String conPassWord, String valismsCode, String code) {
        this.userName = userName;
        this.passWord = passWord;
        this.conPassWord = conPassWord;
        this.valismsCode = valismsCode;
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConPassWord() {
        return conPassWord;
    }

    public void setConPassWord(String conPassWord) {
        this.conPassWord = conPassWord;
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
