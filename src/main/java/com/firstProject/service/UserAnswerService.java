package com.firstProject.service;

import com.firstProject.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerService {
        ResponseEntity<String> createUserAnswer(UserAnswerRequest userAnswerRequest);
        void updateUserAnswer(UserAnswer userAnswer);
        void deleteUserAnswer(Long id);
        void deleteQuestionAnswerByUserId(Long userId);

        SelectedQuestionOptionResponse getUsersChoseQuestionOptionNumber(Long questionId);
        Integer getUsersAnsweredCountByQuestionId(Long questionId);
        List<UserAnswerResponse> getAllUserAnswers(Long userId);
        Integer getNumberOfQuestionsUserAnswered(Long userId);
        List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount();
        User getUserById(Long userId);
}
