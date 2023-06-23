package com.ai.chatpan.data.bean;

import java.util.List;

public class ChatPanBean {

    /**
     * id : 3131
     * uuid : f209353f-449e-4c8e-84f0-2680088f5d5a
     * accountId : 2
     * subjectId : 1717
     * room : {"accountId":2,"uuid":"64f8791f-c1de-427d-990b-9cc7eb8ff472","name":"丰年","assistantUuids":["18914f7a-9a0c-4314-9f3b-365481809b97"],"allowAnonymous":true}
     * assistant : {"uuid":"18914f7a-9a0c-4314-9f3b-365481809b97","name":"丰年","summary":"丰年","type":"QA","defaultAnswer":"","botClassNameList":[],"botConfList":[]}
     * question : 为什么私域要慢慢做?
     * answer : 发现现在有些大主播的直播间里面，他的抖音群经营就有点像当年咱们看到的微信白菜群，因为大主播的直播间里面是不是款式特别多，也有一些性价比比较好的商品，那我不想以后天天在大主播直播间里蹲着去抢，那如果大主播跟我说你加入我的粉丝群，我每天有一些性价比商品给你，是不是也很开心。因为大主播开播频次一周就两次，一周可能就一次，甚至一个月一次，那平时怎么挣钱呢？所以我觉得大主播做抖音私域的意义非常大，但是他们为什么又都做不好？这里面又有一个本质的一个逻辑在里面。就这些人，原来在直播间里买东西，他的成交效率很高，他很爽，那瞬间一场卖几百万上千万可是你现在让他去做私域，他的心态要转化，因为私域是一个长效经营，我们叫长效价值，也就是需要有人坐下来，慢慢地去跟这些私域群里面去聊，去经营，去耕耘，但这种东西很多大主播本人以及他们的操盘手，他根本就不是一种基因的东西，原来是快，现在又慢，私域是长线，所以这个思维的转换是他们最难绕过去的坎。
     */

    private int id;
    private String uuid;
    private int accountId;
    private int subjectId;
    private RoomBean room;
    private AssistantBean assistant;
    private String question;
    private String answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public RoomBean getRoom() {
        return room;
    }

    public void setRoom(RoomBean room) {
        this.room = room;
    }

    public AssistantBean getAssistant() {
        return assistant;
    }

    public void setAssistant(AssistantBean assistant) {
        this.assistant = assistant;
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

    public static class RoomBean {
        /**
         * accountId : 2
         * uuid : 64f8791f-c1de-427d-990b-9cc7eb8ff472
         * name : 丰年
         * assistantUuids : ["18914f7a-9a0c-4314-9f3b-365481809b97"]
         * allowAnonymous : true
         */

        private int accountId;
        private String uuid;
        private String name;
        private boolean allowAnonymous;
        private List<String> assistantUuids;

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isAllowAnonymous() {
            return allowAnonymous;
        }

        public void setAllowAnonymous(boolean allowAnonymous) {
            this.allowAnonymous = allowAnonymous;
        }

        public List<String> getAssistantUuids() {
            return assistantUuids;
        }

        public void setAssistantUuids(List<String> assistantUuids) {
            this.assistantUuids = assistantUuids;
        }
    }

    public static class AssistantBean {
        /**
         * uuid : 18914f7a-9a0c-4314-9f3b-365481809b97
         * name : 丰年
         * summary : 丰年
         * type : QA
         * defaultAnswer :
         * botClassNameList : []
         * botConfList : []
         */

        private String uuid;
        private String name;
        private String summary;
        private String type;
        private String defaultAnswer;
        private List<?> botClassNameList;
        private List<?> botConfList;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDefaultAnswer() {
            return defaultAnswer;
        }

        public void setDefaultAnswer(String defaultAnswer) {
            this.defaultAnswer = defaultAnswer;
        }

        public List<?> getBotClassNameList() {
            return botClassNameList;
        }

        public void setBotClassNameList(List<?> botClassNameList) {
            this.botClassNameList = botClassNameList;
        }

        public List<?> getBotConfList() {
            return botConfList;
        }

        public void setBotConfList(List<?> botConfList) {
            this.botConfList = botConfList;
        }
    }
}
