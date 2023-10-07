package com.firstProject.model;

public class QuestionOptionRequest {
    private PollQuestion question;
    private PollOption option;
    public QuestionOptionRequest(){}

    public QuestionOptionRequest(PollQuestion question, PollOption option) {
        this.question = question;
        this.option = option;
    }

    public PollQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PollQuestion question) {
        this.question = question;
    }

    public PollOption getOption() {
        return option;
    }

    public void setOption(PollOption option) {
        this.option = option;
    }
}
