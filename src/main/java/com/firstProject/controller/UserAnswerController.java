package com.firstProject.controller;

import com.firstProject.model.*;
import com.firstProject.repository.UserAnswerRepository;
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
    @Autowired
    UserAnswerRepository userAnswerRepository;

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUserAnswer(@RequestBody UserAnswerRequest userAnswerRequest) {
        return userAnswerService.createUserAnswer(userAnswerRequest);
    }

    @PutMapping(value = "/update/{userAnswerId}")
    public void updateUserQuestionAnswer(@RequestBody UserAnswer userAnswer) {
        userAnswerService.updateUserAnswer(userAnswer);
    }

    @DeleteMapping(value = "/delete/{userAnswerId}")
    public void deleteUserAnswerById(@PathVariable Long id) {
        userAnswerService.deleteUserAnswer(id);
    }

    @GetMapping(value = "/answerUserCount/{questionId}")
    public SelectedQuestionOptionResponse getUsersChoseAnswerByQuestionId(@PathVariable Long questionId) {
        return userAnswerService.getUsersChoseQuestionOptionNumber(questionId);
    }

    @GetMapping(value = "/getUsersAnsweredNumber/{questionId}")
    public String getNumberOfUsersAnsweredQuestionByQuestionId(@PathVariable Long questionId) {
        return userAnswerService.getUsersAnsweredCountByQuestionId(questionId) + " users have answered this question number " + questionId;
    }

    @GetMapping(value = "/userAnswersAll/{userId}")
    public List<UserAnswerResponse> getAllUserAnswers(@PathVariable Long userId) {
        return userAnswerService.getAllUserAnswers(userId);
    }

    @GetMapping(value = "/questionsNumber/{userId}")
    public String getNumberOfQuestionsUserAnsweredByUserId(@PathVariable Long userId) {
        return "This user has answered " + userAnswerService.getNumberOfQuestionsUserAnswered(userId) + " questions.";
    }

    @GetMapping(value = "/allQuestionsAnswersCounter")
    public List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount() {
        return userAnswerService.getAllQuestionsAndAnswerSelectedCount();
    }

    @GetMapping(value = "/getUserById/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return userServiceClient.getUserById(userId);
    }

    @GetMapping("/isRegistered/{userId}")
    public ResponseEntity<Boolean> isRegistered(@PathVariable Long userId) {
        ResponseEntity<Boolean> isUserRegisteredResponse = userAnswerService.isRegistered(userId);
        return ResponseEntity.ok(isUserRegisteredResponse.getBody());
    }
}
