package com.firstProject.model;

public class UserQuestionAnswerResponse {
    private String question;
    private String selectedAnswer;
    public UserQuestionAnswerResponse(){}

    public UserQuestionAnswerResponse(String question, String selectedAnswer) {
        this.question = question;
        this.selectedAnswer = selectedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
