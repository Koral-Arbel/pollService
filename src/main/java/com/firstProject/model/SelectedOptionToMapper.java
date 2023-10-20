package com.firstProject.model;

import com.firstProject.userService.UserServiceClient;

public class SelectedOptionToMapper {
    private Long optionId;
    private Integer amountAnswersAnswered;

    public SelectedOptionToMapper(Long optionId, Integer amountAnswersAnswered) {
        this.optionId = optionId;
        this.amountAnswersAnswered = amountAnswersAnswered;
    }

    public SelectedOptionToMapper(UserServiceClient optionId, Integer amountAnswersAnswered) {

        this.amountAnswersAnswered = amountAnswersAnswered;
    }

    public Long getOptionId() {
        return optionId;
    }

    public Integer getAmountAnswersAnswered() {
        return amountAnswersAnswered;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public void setAmountAnswersAnswered(Integer amountAnswersAnswered) {
        this.amountAnswersAnswered = amountAnswersAnswered;
    }
}
