package com.firstProject.repository;

import com.firstProject.model.OptionSelectedToMapper;
import com.firstProject.model.UserAnswer;

import java.util.List;

public interface UserAnswerRepository {
    void createUserAnswer(UserAnswer userAnswer);
    void updateUserAnswer(UserAnswer userAnswer);
    void deleteUserAnswer(Long id);
    void deleteQuestionAnswerByUserId(Long userId);
    boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId,Long questionId);
    Integer getUsersAnsweredCountByQuestionId(Long questionId);
    List<OptionSelectedToMapper> getUsersChoseQuestionOptionNumber(Long questionId);
    Integer getNumberOfQuestionsUserAnswered(Long userId);
    List<OptionSelectedToMapper> getAllQuestionsAndAnswerSelectedCount(Long questionId);
    List<UserAnswer> getAllUserAnswers(Long userId);

}
