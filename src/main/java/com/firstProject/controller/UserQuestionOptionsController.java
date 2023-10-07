//package com.firstProject.controller;
//
//import com.firstProject.model.*;
//import com.firstProject.service.UserQuestionAnswerServiceImpl;
//import com.firstProject.userService.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping(value = "/UserQuestionAnswer")
//public class UserQuestionOptionsController {
//
//    @Autowired
//    UserQuestionAnswerServiceImpl userQuestionAnswerService;
//    @Autowired
//    UserService userService;
//
//    @PostMapping(value = "/create")
//    public void createUserQuestionAnswer(@RequestBody PollOption option){
//        userQuestionAnswerService.createQuestionAnswer(option);
//    }
//
//    @PutMapping(value = "/update/{userAnswerId}")
//    public String updateQuestion(@PathVariable Long userAnswerId, @RequestBody QuestionOptionsResponse questionOptionsResponse) {
//        // Assuming you have a method to retrieve the existing UserQuestionAnswer object
//        UserQuestionAnswer userQuestionAnswer = userQuestionAnswerService.getUsersAnsweredByQuestionId(userAnswerId);
//
//        if (userQuestionAnswer == null) {
//            return "User question answer not found for ID: " + userAnswerId;
//        }
//
//// Update the relevant fields in the userQuestionAnswer object based on your requirements
//// For example:
//        userQuestionAnswer.setQuestionId(questionOptionsResponse.getQuestion().getId());
//
//// Perform the update by passing the UserQuestionAnswer object to the service method
//        String updateResult = userQuestionAnswerService.updateQuestion(userQuestionAnswer);
//
//// Return a response based on the update result
//        return updateResult;
//    }
//
//    @DeleteMapping(value = "/delete/{userAnswerId}")
//    public void deleteUserAnswerById(@PathVariable Long userAnswerId){
//        userQuestionAnswerService.deleteQuestionAnswerById(userAnswerId);
//    }
//
//    @DeleteMapping(value = "/deleteAnswers/{userId}")
//    public void deleteUserAnswersByUserId(@PathVariable Long userId){
//        userQuestionAnswerService.deleteQuestionAnswerByUserId(userId);
//    }
//
//    @GetMapping(value = "/userAllAnswers/{userId}")
//    public List<UserQuestionAnswerResponse> getAllUserAnswers(@PathVariable Long userId){
//        return userQuestionAnswerService.getAllUserAnswers(userId);
//    }
//
//    @GetMapping(value = "/getUsersAnsweredNumber/{questionId}")
//    public String getNumberOfUsersAnsweredQuestionByQuestionId(@PathVariable Long questionId){
//        return userQuestionAnswerService.getUsersAnsweredByQuestionId(questionId)+" users have answered this question number "+questionId;
//    }
//
//    @GetMapping(value = "/numberOfUsersWhoAnswered/{questionId}")
//    public UserAnswer getUsersWhoSelectedAnAnswerByQuestionId(@PathVariable Long questionId){
//        return userQuestionAnswerService.getUsersWhoSelectedAnAnswerByQuestionId(questionId);
//    }
//
//    @GetMapping(value = "/answeredByUserId/{userId}")
//    public String getNumberOfQuestionsUserAnsweredByUserId(@PathVariable Long userId){
//        return "This user has answered "+userQuestionAnswerService.getNumberOfQuestionsUserAnswered(userId)+" questions.";
//    }
//
//    @GetMapping(value = "/allQuestionsAnswers")
//    public List<UserQuestionAnswerResponse> getAllQuestionsAndAnswer(@PathVariable Long userId){
//        return userQuestionAnswerService.getAllUserAnswers(userId);
//    }
//
//}
