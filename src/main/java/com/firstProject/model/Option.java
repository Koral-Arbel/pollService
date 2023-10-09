package com.firstProject.model;

public class Option {
    private Long id;
    private String textOption;
    private Long questionId;
    public Option(){}

    public Option(Long id, String textOption, Long questionId) {
        this.id = id;
        this.textOption = textOption;
        this.questionId = questionId;
    }

    public Long getId() {
        return id;
    }

    public String getTextOption() {
        return textOption;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTextOption(String textOption) {
        this.textOption = textOption;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
