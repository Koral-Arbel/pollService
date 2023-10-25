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
    public ResponseEntity<String> createUserAnswer(UserAnswerRequest userAnswerRequest) {
        Long userId = userAnswerRequest.getId();
        ResponseEntity<User> userIdRegistrationResponse = userServiceClient.getUserById(userId);

        if (userIdRegistrationResponse.getStatusCode() == HttpStatus.OK) {
            User isRegistered = userIdRegistrationResponse.getBody();
            if (isRegistered.getId().equals(userId)){
                if (hasUserAnsweredQuestion(userId,1L)) {
                    return ResponseEntity.badRequest().body("User has already answered this question.");
                }

                UserAnswer userAnswer = new UserAnswer();
                userAnswer.setId(userId);
                userAnswer.setQuestionId(1L);
                userAnswer.setSelectedOptionId(2L);

                UserAnswerResponse answerResponse = userAnswerRepository.createUserAnswer(userAnswer);

                if (answerResponse != null) {
                    return ResponseEntity.ok("User Answer created with ID: " + answerResponse.getSelectedAnswer());
                } else {
                    return ResponseEntity.badRequest().body("Failed to create User Answer.");
                }
            } else {
                return ResponseEntity.badRequest().body("User is not registered. Register to be able to answer a question.");
            }
        } else {
            return ResponseEntity.badRequest().body("User not found.");
        }
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
    public User getUserById(Long userId) {
        return userAnswerRepository.getUserById(userId);
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

    private User getUserByEmail(String email) {
        return userServiceClient.getUserByEmail(email);
    }

    private boolean hasUserAnsweredQuestion(Long userId, Long questionId) {
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
}