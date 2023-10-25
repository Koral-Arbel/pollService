package com.firstProject.service;

import com.firstProject.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerService {
        void createUserAnswer(UserAnswer userAnswer);
        void updateUserAnswer(UserAnswer userAnswer);
        void deleteUserAnswer(Long id);
        void deleteQuestionAnswerByUserId(Long userId);

        SelectedQuestionOptionResponse getUsersChoseQuestionOptionNumber(Long questionId);
        Integer getUsersAnsweredCountByQuestionId(Long questionId);
        List<UserAnswerResponse> getAllUserAnswers(Long userId);
        Integer getNumberOfQuestionsUserAnswered(Long userId);
        List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount();
        Boolean hasUserAnsweredQuestion(Long userId, Long questionId);
        User getUserById(Long userId);
        ResponseEntity<Boolean> isRegistered(Long userId);
}
