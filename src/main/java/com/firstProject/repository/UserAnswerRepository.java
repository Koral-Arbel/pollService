package com.firstProject.repository;

import com.firstProject.model.*;

import java.util.List;

public interface UserAnswerRepository {
    UserAnswerResponse createUserAnswer(UserAnswer userAnswer);
    void updateUserAnswer(UserAnswer userAnswer);
    void deleteUserAnswerById(Long userId);
    List<SelectedOptionToMapper> getUsersChoseQuestionOptionNumber(Long questionId);
    Integer getUsersAnsweredCountByQuestionId(Long questionId);
    List<UserAnswer> getAllUserAnswers(Long userId);
    Integer getNumberOfQuestionsUserAnswered(Long userId);
    List<SelectedOptionToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId);
    User getUserById(Long userId);
}
