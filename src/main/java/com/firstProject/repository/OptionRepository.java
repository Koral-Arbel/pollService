package com.firstProject.repository;

import com.firstProject.model.PollOption;
import com.firstProject.model.UserAnswer;

import java.util.List;

public interface OptionRepository {
    Long createOption(UserAnswer userAnswer);
    void updateOption (UserAnswer userAnswer);
    void deleteOptionFromAnswer(Long id);
    PollOption getOptionById(Long id);
    List<PollOption> getOptionsByQuestionId(Long questionId);
}
