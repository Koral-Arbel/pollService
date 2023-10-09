package com.firstProject.model;

public class QuestionOptionResponse {
    private Question question;
    private Option option1;
    private Option option2;
    private Option option3;
    private Option option4;

    public QuestionOptionResponse(Question question, Option option1, Option option2, Option option3, Option option4) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public Question getQuestion() {
        return question;
    }

    public Option getOption1() {
        return option1;
    }

    public Option getOption2() {
        return option2;
    }

    public Option getOption3() {
        return option3;
    }

    public Option getOption4() {
        return option4;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setOption1(Option option1) {
        this.option1 = option1;
    }

    public void setOption2(Option option2) {
        this.option2 = option2;
    }

    public void setOption3(Option option3) {
        this.option3 = option3;
    }

    public void setOption4(Option option4) {
        this.option4 = option4;
    }
}
