package com.firstProject.model;

import java.util.List;

public class OptionSelectedForMap {
    private Long answerId;
    private List optionSelected;

    public OptionSelectedForMap(){}

    public OptionSelectedForMap(Long answerId, List optionSelected) {
        this.answerId = answerId;
        this.optionSelected = optionSelected;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public List getOptionSelected() {
        return optionSelected;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public void setOptionSelected(List optionSelected) {
        this.optionSelected = optionSelected;
    }
}
