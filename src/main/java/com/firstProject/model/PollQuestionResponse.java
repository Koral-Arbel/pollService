package com.firstProject.model;

public class PollQuestionResponse {
    private String message;
    private Long questionId;

    public PollQuestionResponse() {}

    public PollQuestionResponse(String message, Long questionId) {
        this.message = message;
        this.questionId = questionId;
    }

    public String getMessage() {
        return message;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
