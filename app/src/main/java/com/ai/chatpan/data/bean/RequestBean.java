package com.ai.chatpan.data.bean;

public class RequestBean {


    public RequestBean(String subjectId, String outerId, String roomUUID, String assistantUUID, String question) {
        this.subjectId = subjectId;
        this.outerId = outerId;
        this.roomUUID = roomUUID;
        this.assistantUUID = assistantUUID;
        this.question = question;
    }

    /**
     * outerId : ${房间 uuid}
     * roomUUID : ${房间 uuid}
     * assistantUUID : ${第三方用户id，本场景下是丰年APP的用户id，这个 id 只和 丰年在 ChatPAN.ai 的主账号进行绑定。通过 outerId 可以统计不同的用户提问的次数、历史提问记录等}
     * question : ${用户输入的文本、问题}
     */

    private String subjectId;

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    private String outerId;
    private String roomUUID;
    private String assistantUUID;
    private String question;

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getRoomUUID() {
        return roomUUID;
    }

    public void setRoomUUID(String roomUUID) {
        this.roomUUID = roomUUID;
    }

    public String getAssistantUUID() {
        return assistantUUID;
    }

    public void setAssistantUUID(String assistantUUID) {
        this.assistantUUID = assistantUUID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "RequestBean{" +
                "subjectId='" + subjectId + '\'' +
                "outerId='" + outerId + '\'' +
                ", roomUUID='" + roomUUID + '\'' +
                ", assistantUUID='" + assistantUUID + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
