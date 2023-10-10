package com.firstProject.service;

import com.firstProject.model.*;

import java.util.List;

public interface UserAnswerService {
        void createUserAnswer(UserAnswerRequest userAnswerRequest);
        void updateUserAnswer(UserAnswerRequest userAnswerRequest);
        void deleteUserAnswer(Long id);
        void deleteQuestionAnswerByUserId(Long userId);
        Boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId,Long questionId);
        Integer getUsersAnsweredCountByQuestionId(Long questionId);
        QuestionOptionSelectedResponse getUsersChoseQuestionOptionNumber(Long questionId);
        Integer getNumberOfQuestionsUserAnswered(Long userId);
        List<QuestionOptionSelectedResponse> getAllQuestionsAndAnswerSelectedCount();
        List<UserAnswerResponse> getAllUserAnswers(Long userId);

}
