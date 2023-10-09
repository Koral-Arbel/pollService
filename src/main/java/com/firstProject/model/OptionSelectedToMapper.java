package com.firstProject.model;

public class OptionSelectedToMapper {
    private Long answerId;
    private Integer countingAnswers;

    public OptionSelectedToMapper(Long answerId, Integer countingAnswers) {
        this.answerId = answerId;
        this.countingAnswers = countingAnswers;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public Integer getCountingAnswers() {
        return countingAnswers;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setCountingAnswers(Integer countingAnswers) {
        this.countingAnswers = countingAnswers;
    }
}
