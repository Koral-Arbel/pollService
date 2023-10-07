package com.firstProject.model;

public class QuestionOptionsResponse {
    private PollQuestion pollQuestion;
    private UserAnswer firstUserAnswer;
    private UserAnswer secondUserAnswer;
    private UserAnswer thirdUserAnswer;
    private UserAnswer fourthUserAnswer;

    public QuestionOptionsResponse(PollQuestion pollQuestion, UserAnswer firstUserAnswer, UserAnswer secondUserAnswer, UserAnswer thirdUserAnswer, UserAnswer fourthUserAnswer) {
        this.pollQuestion = pollQuestion;
        this.firstUserAnswer = firstUserAnswer;
        this.secondUserAnswer = secondUserAnswer;
        this.thirdUserAnswer = thirdUserAnswer;
        this.fourthUserAnswer = fourthUserAnswer;
    }

    public PollQuestion getQuestion() {
        return pollQuestion;
    }

    public UserAnswer getFirstAnswer() {
        return firstUserAnswer;
    }

    public UserAnswer getSecondAnswer() {
        return secondUserAnswer;
    }

    public UserAnswer getThirdAnswer() {
        return thirdUserAnswer;
    }

    public UserAnswer getFourthAnswer() {
        return fourthUserAnswer;
    }

    public void setQuestion(PollQuestion pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public void setFirstAnswer(UserAnswer firstUserAnswer) {
        this.firstUserAnswer = firstUserAnswer;
    }

    public void setSecondAnswer(UserAnswer secondUserAnswer) {
        this.secondUserAnswer = secondUserAnswer;
    }

    public void setThirdAnswer(UserAnswer thirdUserAnswer) {
        this.thirdUserAnswer = thirdUserAnswer;
    }

    public void setFourthAnswer(UserAnswer fourthUserAnswer) {
        this.fourthUserAnswer = fourthUserAnswer;
    }

}
