package com.firstProject.repository;

import com.firstProject.model.Option;

import java.util.List;

public interface OptionRepository {
    Long createOption(Option option);
    void updateOption (Option option);
    void deleteOptionFromAnswer(Long id);
    Option getOptionById(Long id);
    List<Option> getOptionsByQuestionId(Long questionId);

}
