package com.firstProject.service;

import com.firstProject.model.Option;

import java.util.List;

public interface PollOptionService {
    Option createOption (Option option);
    Option updateOption (Option option);
    void deleteOption (Long id);
    Option getOptionById(Long id);
    List<Option> getOptionsByQuestionId(Long questionId);
}
