package com.firstProject.service;

import com.firstProject.model.*;
import com.firstProject.repository.UserAnswerRepositoryImpl;
import com.firstProject.userService.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

        // Check if the user has already answered the question
        if (userAnswerRepository.hasUserAnsweredQuestion(userAnswer.getUserId(), userAnswer.getQuestionId())) {
            throw new IllegalArgumentException("User has already answered this question.");
        }

        // Create the UserAnswer object and store it
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
    public void deleteUserAnswer(Long id) {
        userAnswerRepository.deleteUserAnswerById(id);
    }

    @Override
    public void deleteQuestionAnswerByUserId(Long userId) {
        userAnswerRepository.deleteUserAnswerById(userId);
    }
    @Override
    public SelectedQuestionOptionResponse getUsersChoseQuestionOptionNumber(Long questionId) {
        List<SelectedOptionToMapper> answerChosenToMaps = userAnswerRepository.getUsersChoseQuestionOptionNumber(questionId);
        QuestionOptionResponse questions = questionService.getQuestionById(questionId);
        String questionText = questions.getQuestion().getTitle();
        List<SelectedOption> answers = new ArrayList<>();
        answers.add(new SelectedOption(questions.getOption1().getTextOption(), 0));
        answers.add(new SelectedOption(questions.getOption2().getTextOption(), 0));
        answers.add(new SelectedOption(questions.getOption3().getTextOption(), 0));
        answers.add(new SelectedOption(questions.getOption4().getTextOption(), 0));

        for (int i = 0; i < answerChosenToMaps.size(); i++) {
            Option option = optionService.getOptionById(answerChosenToMaps.get(i).getOptionId());
            for (int x = 0; x < answers.size(); x++) {
                if (answers.get(x).getTextOption() == option.getTextOption()) {
                    answers.get(x).setSelectedAnswer(answerChosenToMaps.get(i).getAmountAnswersAnswered());
                }
            }
        }
        return new SelectedQuestionOptionResponse(questionText, answers);
    }

    @Override
    public Integer getUsersAnsweredCountByQuestionId(Long questionId) {
        return userAnswerRepository.getUsersAnsweredCountByQuestionId(questionId);
    }

    @Override
    public List<UserAnswerResponse> getAllUserAnswers(Long userId) {
        List<UserAnswerResponse> responseList=new ArrayList<>();
        List<UserAnswer> list=userAnswerRepository.getAllUserAnswers(userId);

        for(var questionAnswer:list){
            String question=questionService.getQuestionById(questionAnswer.getQuestionId()).getQuestion().getTitle();
            String answer= optionService.getOptionById(questionAnswer.getQuestionId()).getTextOption();
            responseList.add(new UserAnswerResponse());
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