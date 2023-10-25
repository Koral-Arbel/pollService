package com.firstProject.model;

import org.springframework.stereotype.Component;

@Component
public class UserAnswerRequest {
    private Long id;
    private String email;
    private QuestionOptionRequest questionOptionRequest;
    private Option option;

    public UserAnswerRequest(Long id, String email, QuestionOptionRequest questionOptionRequest, Option option) {
        this.id = id;
        this.email = email;
        this.questionOptionRequest = questionOptionRequest;
        this.option = option;
    }

    public UserAnswerRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public QuestionOptionRequest getQuestionOptionRequest() {
        return questionOptionRequest;
    }

    public Option getOption() {
        return option;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuestionOptionRequest(QuestionOptionRequest questionOptionRequest) {
        this.questionOptionRequest = questionOptionRequest;
    }

    public void setOption(Option option) {
        this.option = option;
    }
    }
