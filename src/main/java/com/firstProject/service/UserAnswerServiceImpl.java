package com.firstProject.service;

import com.firstProject.model.*;
import com.firstProject.repository.UserAnswerRepositoryImpl;
import com.firstProject.userService.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserAnswerServiceImpl implements UserAnswerService {
    @Autowired
    UserAnswerRepositoryImpl userAnswerRepository;
    @Autowired
    UserServiceClient userServiceClient;
    @Autowired
    PollQuestionService questionService;
    @Autowired
    PollOptionService optionService;


    @Override
    public void createUserAnswer(UserAnswer userAnswer) {
        ResponseEntity<Boolean> isRegisteredResponse = userServiceClient.isRegistered(userAnswer.getUserId());
        if (isRegisteredResponse.getStatusCode() != HttpStatus.OK || !isRegisteredResponse.getBody()) {
            throw new IllegalArgumentException("User is not registered.");
        }
        if (userAnswerRepository.hasUserAnsweredQuestion(userAnswer.getUserId(), userAnswer.getQuestionId())) {
            throw new IllegalArgumentException("User has already answered this question.");
        }
        UserAnswer userAnswerResponse = new UserAnswer();
        userAnswerResponse.setId(userAnswer.getId());
        userAnswerResponse.setUserId(userAnswer.getUserId());
        userAnswerResponse.setQuestionId(userAnswer.getQuestionId());
        userAnswerResponse.setSelectedOptionId(userAnswer.getSelectedOptionId());

        userAnswerRepository.createUserAnswer(userAnswerResponse);
    }

    @Override
    public void updateUserAnswer(UserAnswer userAnswer) {
        userAnswerRepository.updateUserAnswer(userAnswer);
    }

    @Override
    public void deleteUserAnswerById(Long userId) {
        List<UserAnswer> userAnswers = userAnswerRepository.getAllUserAnswers(userId);
        for (UserAnswer userAnswer : userAnswers) {
            userAnswerRepository.deleteUserAnswerById(userAnswer.getId());
        }
    }

    @Override
    public SelectedQuestionOptionResponse getUsersChoseQuestionOptionNumber(Long questionId) {
        List<SelectedOptionToMapper> answerChosenToMaps = userAnswerRepository.getUsersChoseQuestionOptionNumber(questionId);
        QuestionOptionResponse question = questionService.getQuestionById(questionId);
        String questionText = question.getQuestion().getTitle();
        List<SelectedOption> answers = new ArrayList<>();
        // יצירת מיפוי שמשרה את הטקסט של האופציה כמפתח עם ערך הכמות שנבחרו כערך
        Map<String, Integer> answerCountMap = new HashMap<>();
        for (SelectedOptionToMapper answer : answerChosenToMaps) {
            Option option = optionService.getOptionById(answer.getOptionId());
            answerCountMap.put(option.getTextOption(), answer.getAmountAnswersAnswered());
        }
        // הוספת האופציות והכמויות לרשימת התשובות
        answers.add(new SelectedOption(question.getOption1().getTextOption(), answerCountMap.getOrDefault(question.getOption1().getTextOption(), 0)));
        answers.add(new SelectedOption(question.getOption2().getTextOption(), answerCountMap.getOrDefault(question.getOption2().getTextOption(), 0)));
        answers.add(new SelectedOption(question.getOption3().getTextOption(), answerCountMap.getOrDefault(question.getOption3().getTextOption(), 0)));
        answers.add(new SelectedOption(question.getOption4().getTextOption(), answerCountMap.getOrDefault(question.getOption4().getTextOption(), 0)));
        return new SelectedQuestionOptionResponse(questionText, answers);
    }


    @Override
    public Integer getUsersAnsweredCountByQuestionId(Long questionId) {
        return userAnswerRepository.getUsersAnsweredCountByQuestionId(questionId);
    }

    @Override
    public List<UserAnswerResponse> getAllUserAnswers(Long userId) {
        List<UserAnswerResponse> responseList = new ArrayList<>();
        List<UserAnswer> list = userAnswerRepository.getAllUserAnswers(userId);

        for (var questionAnswer : list) {
            Long questionId = questionAnswer.getQuestionId();
            Question question = questionService.getQuestionById(questionId).getQuestion();
            String answers = questionAnswer.getSelectedOptionId().toString();

            String questionText = question.getTitle();

            responseList.add(new UserAnswerResponse(questionId, questionText, answers));
        }
        return responseList;
    }

    @Override
    public Integer getNumberOfQuestionsUserAnswered(Long userId) {
        return userAnswerRepository.getNumberOfQuestionsUserAnswered(userId);
    }

    @Override
    public List<SelectedQuestionOptionResponse> getAllQuestionsAndAnswerSelectedCount() {
        List<QuestionOptionResponse> questions=questionService.getAllPoll();
        List<SelectedQuestionOptionResponse> questionsResponse=new ArrayList<>();

        for (var question:questions) {
            try {
                SelectedQuestionOptionResponse response= getUsersChoseQuestionOptionNumber(question.getQuestion().getId());
                questionsResponse.add(response);
            }catch (Exception err){

            }
        }
        return questionsResponse;
    }


    public Boolean hasUserAnsweredQuestion(Long userId, Long questionId) {
        ResponseEntity<User> userResponse = userServiceClient.getUserById(userId);
        if (userResponse.getStatusCode() == HttpStatus.OK) {
            User user = userResponse.getBody();
            List<UserAnswer> userAnswers = userAnswerRepository.getAllUserAnswers(user.getId());
            for (UserAnswer userAnswer : userAnswers) {
                if (userAnswer.getQuestionId().equals(questionId)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public User getUserById(Long userId) {
        User user = userServiceClient.getUserById(userId).getBody();
        if (user != null) {
            return userAnswerRepository.getUserById(userId);
        }
        return null;
    }

    @Override
    public ResponseEntity<Boolean> isRegistered(Long userId) {
        return userServiceClient.isRegistered(userId);
    }
}