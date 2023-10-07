package com.firstProject.model;

public class UserQuestionAnswer {
    private Long id;
    private Long userId;
    private Long questionId;
    private Long answerId;
    public UserQuestionAnswer(){}

    public UserQuestionAnswer(Long id, Long userId, Long questionId, Long answerId) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
