package com.firstProject.service;

import com.firstProject.model.UserAnswer;

import java.util.List;

public interface OptionService {
    UserAnswer createOption(UserAnswer userAnswer);
    UserAnswer updateOption (UserAnswer userAnswer);
    void deleteOptionFromAnswer(Long id);
    UserAnswer getOptionById(Long id);
    List<UserAnswer> getOptionsByQuestionId(Long questionId);
}
