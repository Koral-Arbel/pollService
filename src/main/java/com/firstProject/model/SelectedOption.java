package com.firstProject.model;

public class SelectedOption {
    private String textOption;
    private Integer selectedAnswer;

    public SelectedOption(String textOption, Integer selectedAnswer) {
        this.textOption = textOption;
        this.selectedAnswer = selectedAnswer;
    }

    public String getTextOption() {
        return textOption;
    }

    public Integer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setTextOption(String textOption) {
        this.textOption = textOption;
    }

    public void setSelectedAnswer(Integer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
