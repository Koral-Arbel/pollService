package com.firstProject.controller;

import com.firstProject.model.*;
import com.firstProject.service.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/userQuestionAnswer")
public class UserAnswerController {


    @Autowired
    UserAnswerService userAnswerService;

    @PostMapping(value = "/create")
    public void createUserQuestionAnswer(@RequestBody UserAnswerRequest userAnswerRequest){
        userAnswerService.createUserAnswer(userAnswerRequest);
    }

    @PutMapping(value = "/update/{userAnswerId}")
    public void updateUserQuestionAnswer(@PathVariable Long userAnswerId,@RequestBody UserAnswerRequest userAnswerRequest){
        userAnswerService.updateUserAnswer(userAnswerRequest);
    }

    @DeleteMapping(value = "/delete/{userAnswerId}")
    public void deleteUserAnswerById(@PathVariable Long userAnswerId){
        userAnswerService.deleteUserAnswer(userAnswerId);
    }

    @DeleteMapping(value = "/deleteAnswers/{userId}")
    public void deleteUserAnswersByUserId(@PathVariable Long userId){
        userAnswerService.deleteQuestionAnswerByUserId(userId);
    }

    @GetMapping(value = "/getUsersAnsweredNumber/{questionId}")
    public String getNumberOfUsersAnsweredQuestionByQuestionId(@PathVariable Long questionId){
        return userAnswerService.getUsersAnsweredCountByQuestionId(questionId)+" users have answered this question number "+questionId;
    }

    @GetMapping(value = "/answerUserCount/{questionId}")
    public QuestionOptionSelectedResponse getUsersChoseAnswerByQuestionId(@PathVariable Long questionId){
        return userAnswerService.getUsersChoseQuestionOptionNumber(questionId);
    }

    @GetMapping(value = "/questionsNumber/{userId}")
    public String getNumberOfQuestionsUserAnsweredByUserId(@PathVariable Long userId){
        return "This user has answered "+ userAnswerService.getNumberOfQuestionsUserAnswered(userId)+" questions.";
    }

    @GetMapping(value = "/allQuestionsAnswersCounter")
    public List<QuestionOptionSelectedResponse> getAllQuestionsAndAnswerSelectedCount(){
        return userAnswerService.getAllQuestionsAndAnswerSelectedCount();
    }

    @GetMapping(value = "/userAnswersAll/{userId}")
    public List<UserAnswerResponse> getAllUserAnswers(@PathVariable Long userId){
        return userAnswerService.getAllUserAnswers(userId);
    }
}
