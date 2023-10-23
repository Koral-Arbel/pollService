package com.firstProject.service;

import com.firstProject.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserAnswerService {
        void createUserAnswer(UserAnswerRequest userAnswerRequest);
        void updateUserAnswer(UserAnswerRequest userAnswerRequest);
        void deleteUserAnswer(Long id);
        void deleteQuestionAnswerByUserId(Long userId);

        SelectedQuestionOptionResponse getUsersChoseQuestionOptionNumber(Long questionId);
        Integer getUsersAnsweredCountByQuestionId(Long questionId);
        List<UserAnswerResponse> getAllUserAnswers(Long userId);
        Integer getNumberOfQuestionsUserAnswered(Long userId);
        List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount();



        Boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId,Long questionId);
        ResponseEntity<User> getUserByEmail(String email);
}
