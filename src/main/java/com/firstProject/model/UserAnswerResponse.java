package com.firstProject.model;

public class UserAnswerResponse {
    private Long id;
    private String question;
    private String selectedAnswer;
    public UserAnswerResponse(){}

    public UserAnswerResponse(Long id, String question, String selectedAnswer) {
        this.id = id;
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }


}
