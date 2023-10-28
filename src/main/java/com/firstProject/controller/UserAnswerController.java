package com.firstProject.controller;

import com.firstProject.model.*;
import com.firstProject.service.UserAnswerService;
import com.firstProject.userService.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/userAnswer")
public class UserAnswerController {


    @Autowired
    UserAnswerService userAnswerService;
    @Autowired
    UserServiceClient userServiceClient;

    @PostMapping("/create")
    public void createUserAnswer(@RequestBody UserAnswer userAnswer) {
        userAnswerService.createUserAnswer(userAnswer);
    }

    @PutMapping("/update/{userId}")
    public void updateUserQuestionAnswer(@RequestBody UserAnswer userAnswer) {
        userAnswerService.updateUserAnswer(userAnswer);
    }

    @DeleteMapping("user/deleteUser/{userId}")
    public void deleteUserAnswerById(@PathVariable Long userId) {
        userAnswerService.deleteUserAnswerById(userId);
    }
    @GetMapping("/answerUserCount/{questionId}")
    public SelectedQuestionOptionResponse getUsersChoseAnswerByQuestionId(@PathVariable Long questionId) {
        return userAnswerService.getUsersChoseQuestionOptionNumber(questionId);
    }

    @GetMapping("/getUsersAnsweredNumber/{questionId}")
    public String getNumberOfUsersAnsweredQuestionByQuestionId(@PathVariable Long questionId) {
        return userAnswerService.getUsersAnsweredCountByQuestionId(questionId) + " users have answered this question number " + questionId;
    }

    @GetMapping("/userAnswersAll/{userId}")
    public List<UserAnswerResponse> getAllUserAnswers(@PathVariable Long userId) {
        return userAnswerService.getAllUserAnswers(userId);
    }

    @GetMapping("/questionsNumber/{userId}")
    public String getNumberOfQuestionsUserAnsweredByUserId(@PathVariable Long userId) {
        return "This user has answered " + userAnswerService.getNumberOfQuestionsUserAnswered(userId) + " questions.";
    }

    @GetMapping("/allQuestionsAnswersCounter")
    public List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount() {
        return userAnswerService.getAllQuestionsAndAnswerSelectedCount();
    }

    @GetMapping("/user/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userServiceClient.getUserById(userId);
    }

    @GetMapping("/user/isRegistered/{userId}")
    public ResponseEntity<Boolean> isRegistered(@PathVariable Long userId) {
        ResponseEntity<Boolean> isUserRegisteredResponse = userAnswerService.isRegistered(userId);
        return ResponseEntity.ok(isUserRegisteredResponse.getBody());
    }
}
