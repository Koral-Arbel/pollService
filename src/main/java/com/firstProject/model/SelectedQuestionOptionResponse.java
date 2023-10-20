package com.firstProject.model;

import java.util.List;

public class SelectedQuestionOptionResponse {
    private String titleQuestion;
    List<SelectedOption> selectOption;

    public SelectedQuestionOptionResponse(String titleQuestion, List<SelectedOption> selectOption) {
        this.titleQuestion = titleQuestion;
        this.selectOption = selectOption;
    }

    public String getTitleQuestion() {
        return titleQuestion;
    }

    public List<SelectedOption> getSelectOption() {
        return selectOption;
    }

    public void setTitleQuestion(String titleQuestion) {
        this.titleQuestion = titleQuestion;
    }

    public void setSelectOption(List<SelectedOption> selectOption) {
        this.selectOption = selectOption;
    }
}
