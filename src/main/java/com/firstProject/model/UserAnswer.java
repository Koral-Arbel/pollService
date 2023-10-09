package com.firstProject.model;

import org.springframework.stereotype.Component;

@Component
public class UserAnswer {
    private Long id;
    private Long userId;
    private Long questionId;
    private Long selectedOptionId;

    public UserAnswer() {
    }

    public UserAnswer(Long id, Long userId, Long questionId, Long selectedOptionId) {
        this.id = id;
        this.userId = userId;
        this.questionId = questionId;
        this.selectedOptionId = selectedOptionId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getSelectedOptionId() {
        return selectedOptionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setSelectedOptionId(Long selectedOptionId) {
        this.selectedOptionId = selectedOptionId;
    }
}