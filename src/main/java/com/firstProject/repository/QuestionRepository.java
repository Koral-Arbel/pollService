package com.firstProject.repository;

import com.firstProject.model.PollQuestion;

import java.util.List;

public interface QuestionRepository {
    Long createQuestion (PollQuestion question);
    PollQuestion updateQuestion (PollQuestion question);
    void DeleteQuestion (Long id);
    PollQuestion getQuestionById(Long id);
    List <PollQuestion> getQuestionsList();
}
