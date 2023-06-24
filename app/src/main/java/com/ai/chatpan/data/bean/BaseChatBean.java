package com.ai.chatpan.data.bean;

public class BaseChatBean {

    private String question;
    private String answer;

    private String uuid;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "BaseChatBean{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", uuid='" + uuid + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
