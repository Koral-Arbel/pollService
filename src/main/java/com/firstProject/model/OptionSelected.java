package com.firstProject.model;

public class OptionSelected {
    private String textOption;
    private Integer selectedAnswer;

    public OptionSelected(String textOption, Integer selectedAnswer) {
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
