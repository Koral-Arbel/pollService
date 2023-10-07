package com.firstProject.service;

import com.firstProject.model.PollOption;
import com.firstProject.model.PollQuestion;

import java.util.List;

public interface QuestionService {
    String createQuestion(PollQuestion question, PollOption option);
    List<PollQuestion> getAllQuestions();
    PollQuestion getQuestionById(Long id);
    PollQuestion updateQuestion(Long id, PollQuestion updatedPollQuestion);
    void deleteQuestion(Long id);

}
