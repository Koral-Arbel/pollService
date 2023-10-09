package com.firstProject.model;

import java.util.List;

public class QuestionOptionSelectedResponse {
    private String titleQuestion;
    List<OptionSelected> selectOption;

    public QuestionOptionSelectedResponse(String titleQuestion, List<OptionSelected> selectOption) {
        this.titleQuestion = titleQuestion;
        this.selectOption = selectOption;
    }

    public String getTitleQuestion() {
        return titleQuestion;
    }

    public List<OptionSelected> getSelectOption() {
        return selectOption;
    }

    public void setTitleQuestion(String titleQuestion) {
        this.titleQuestion = titleQuestion;
    }

    public void setSelectOption(List<OptionSelected> selectOption) {
        this.selectOption = selectOption;
    }
}
