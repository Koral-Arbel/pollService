package com.firstProject.repository;

import com.firstProject.model.Question;

import java.util.List;

public interface QuestionRepository {
    Long createQuestion (Question question);
    Question updateQuestion (Question question);
    void deleteQuestion(Long id);

    Question getQuestionById(Long id);
    List<Question> getAllPoll();
}
