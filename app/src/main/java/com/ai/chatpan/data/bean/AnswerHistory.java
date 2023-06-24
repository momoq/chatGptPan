package com.ai.chatpan.data.bean;

public class AnswerHistory extends BaseChatBean{


    /**
     * uuid : 72fadd02-d97f-4bd6-b34d-a7191cfa7163
     * createdAt : 2023-06-21T06:01:40.949763
     * question : 为什么私域要慢慢做?
     * answer : 发现现在有些大主播的直播间里面，他的抖音群经营就有点像当年咱们看到的微信白菜群，因为大主播的直播间里面是不是款式特别多，也有一些性价比比较好的商品，那我不想以后天天在大主播直播间里蹲着去抢，那如果大主播跟我说你加入我的粉丝群，我每天有一些性价比商品给你，是不是也很开心。因为大主播开播频次一周就两次，一周可能就一次，甚至一个月一次，那平时怎么挣钱呢？所以我觉得大主播做抖音私域的意义非常大，但是他们为什么又都做不好？这里面又有一个本质的一个逻辑在里面。就这些人，原来在直播间里买东西，他的成交效率很高，他很爽，那瞬间一场卖几百万上千万可是你现在让他去做私域，他的心态要转化，因为私域是一个长效经营，我们叫长效价值，也就是需要有人坐下来，慢慢地去跟这些私域群里面去聊，去经营，去耕耘，但这种东西很多大主播本人以及他们的操盘手，他根本就不是一种基因的东西，原来是快，现在又慢，私域是长线，所以这个思维的转换是他们最难绕过去的坎。
     */

    private String uuid;
    private String createdAt;
    private String question;
    private String answer;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "AnswerHistory{" +
                "uuid='" + uuid + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

}
