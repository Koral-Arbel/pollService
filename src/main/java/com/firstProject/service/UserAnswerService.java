package com.firstProject.service;

import com.firstProject.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerService {
        void createUserAnswer(UserAnswerRequest userAnswerRequest);
        void updateUserAnswer(UserAnswerRequest userAnswerRequest);
        void deleteUserAnswer(Long id);
        void deleteQuestionAnswerByUserId(Long userId);
        Boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId,Long questionId);
        Integer getUsersAnsweredCountByQuestionId(Long questionId);
        SelectedQuestionOptionResponse getUsersChoseQuestionOptionNumber(Long questionId);
        Integer getNumberOfQuestionsUserAnswered(Long userId);
        List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount();
        List<UserAnswerResponse> getAllUserAnswers(Long userId);


        ResponseEntity<User> getUserByEmail(String email);
}
