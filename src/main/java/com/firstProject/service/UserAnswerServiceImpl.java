package com.firstProject.service;

import com.firstProject.model.*;
import com.firstProject.repository.UserAnswerRepositoryImpl;
import com.firstProject.userService.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUserAnswer(UserAnswerRequest userAnswerRequest) {
        User user = userServiceClient.getUserByEmail(userAnswerRequest.getEmail()).getBody();

        if(!checkIfUserAnsweredQuestionByUserIdAndQuestionId(user.getId(),userAnswerRequest.getQuestionOptionRequest().getQuestion().getId())) {
            boolean isRegistered = userServiceClient.getUserByEmail(userAnswerRequest.getEmail()).hasBody(); // נשתמש ישירות בערך במשתנה בוליאני

            if (isRegistered) {
                userAnswerRepository.createUserAnswer(userAnswerRequest.toUserAnswer(
                        user.getId(),
                        userAnswerRequest.getQuestionOptionRequest().getQuestion().getId(),
                        userAnswerRequest.getOption().getId()
                ));
            }
        }
    }

    @Override
    public void updateUserAnswer(UserAnswerRequest userAnswerRequest) {
        User user = userServiceClient.getUserByEmail(userAnswerRequest.getEmail()).getBody();
        userAnswerRepository.updateUserAnswer(userAnswerRequest.toUserAnswer(
                user.getId(),
                userAnswerRequest.getOption().getQuestionId(),
                userAnswerRequest.getOption().getId()
        ));
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
    public ResponseEntity<User> getUserByEmail(String email) {
        return userAnswerRepository.getUserByEmail(email);
    }

    @Override
    public Boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.checkIfUserAnsweredQuestionByUserIdAndQuestionId(userId, questionId);
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
}