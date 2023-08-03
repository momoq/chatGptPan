package com.ai.chatpan.data.bean;

public class WXEventBean {
    private String wxCode ;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    @Override
    public String toString() {
        return "WXEventBean{" +
                "wxCode='" + wxCode + '\'' +
                '}';
    }
}
