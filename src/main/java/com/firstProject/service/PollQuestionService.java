package com.firstProject.service;

import com.firstProject.model.Question;
import com.firstProject.model.QuestionOptionRequest;
import com.firstProject.model.QuestionOptionResponse;

import java.util.List;

public interface PollQuestionService {
    QuestionOptionResponse createQuestion (QuestionOptionRequest questionOptionRequest);
    Question updateQuestion (Question question);
    void deleteQuestion (Long id);
    QuestionOptionResponse getQuestionById(Long id);
    List<QuestionOptionResponse> getQuestionsList();
}
