package com.firstProject.controller;

import com.firstProject.model.*;
import com.firstProject.repository.UserAnswerRepository;
import com.firstProject.service.UserAnswerService;
import com.firstProject.userService.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> createUserQuestionAnswer(@RequestParam UserAnswerRequest userAnswerRequest) {
        try {
            // ביצוע קריאה לשירות כדי לבדוק אם המשתמש רשום
            UserServiceClient user = (UserServiceClient) userServiceClient.getUserAnswerByEmail(userAnswerRequest.getEmail());
            userAnswerService.createUserAnswer(userAnswerRequest);


            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("המשתמש לא רשום במערכת.");
            }
            if (userAnswerService.checkIfUserAnsweredQuestionByUserIdAndQuestionId(userAnswerRequest.getId(), userAnswerRequest.getQuestionOptionRequest().getQuestion().getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("המשתמש כבר ענה על שאלה זו.");
            }

            // ביצוע קריאה נוספת לשירות כדי לבדוק האם המשתמש רשום
            boolean isRegistered = userServiceClient.getRegistered();

            if (isRegistered) {
                // שמירת התשובה במאגר הנתונים
                userAnswerRepository.createUserAnswer(userAnswerRequest.toUserAnswer(
                        userAnswerRequest.getId(),
                        userAnswerRequest.getQuestionOptionRequest().getQuestion().getId(),
                        userAnswerRequest.getOption().getId()
                ));

                return ResponseEntity.status(HttpStatus.OK).body("התשובה נשמרה בהצלחה.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("המשתמש לא רשום במערכת.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("שגיאת שרת: " + e.getMessage());
        }
    }

    @PutMapping(value = "/update/{userAnswerId}")
    public void updateUserQuestionAnswer(@PathVariable Long userAnswerId, @RequestBody UserAnswerRequest userAnswerRequest) {
        userAnswerService.updateUserAnswer(userAnswerRequest);
    }

    @DeleteMapping(value = "/delete/{userAnswerId}")
    public void deleteUserAnswerById(@PathVariable Long userAnswerId) {
        userAnswerService.deleteUserAnswer(userAnswerId);
    }

    @DeleteMapping(value = "/deleteAnswers/{userId}")
    public void deleteUserAnswersByUserId(@PathVariable Long userId) {
        userAnswerService.deleteQuestionAnswerByUserId(userId);
    }

    @GetMapping(value = "/getUsersAnsweredNumber/{questionId}")
    public String getNumberOfUsersAnsweredQuestionByQuestionId(@PathVariable Long questionId) {
        return userAnswerService.getUsersAnsweredCountByQuestionId(questionId) + " users have answered this question number " + questionId;
    }

    @GetMapping(value = "/answerUserCount/{questionId}")
    public QuestionOptionSelectedResponse getUsersChoseAnswerByQuestionId(@PathVariable Long questionId) {
        return userAnswerService.getUsersChoseQuestionOptionNumber(questionId);
    }

    @GetMapping(value = "/questionsNumber/{userId}")
    public String getNumberOfQuestionsUserAnsweredByUserId(@PathVariable Long userId) {
        return "This user has answered " + userAnswerService.getNumberOfQuestionsUserAnswered(userId) + " questions.";
    }

    @GetMapping(value = "/allQuestionsAnswersCounter")
    public List<QuestionOptionSelectedResponse> getAllQuestionsAndAnswerSelectedCount() {
        return userAnswerService.getAllQuestionsAndAnswerSelectedCount();
    }

    @GetMapping(value = "/userAnswersAll/{userId}")
    public List<UserAnswerResponse> getAllUserAnswers(@PathVariable Long userId) {
        return userAnswerService.getAllUserAnswers(userId);
    }
}
