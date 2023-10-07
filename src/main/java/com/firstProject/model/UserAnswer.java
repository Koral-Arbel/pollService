package com.firstProject.model;

public class UserAnswer {
    private Long id;
    private String option;
    private PollQuestion question;

    public UserAnswer(){}

    public UserAnswer(Long id, String option, PollQuestion question) {
        this.id=id;
        this.option = option;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public PollQuestion getQuestion() {
        return question;
    }

    public void setQuestion(PollQuestion question) {
        this.question = question;
    }
}
