package com.firstProject.service;

import com.firstProject.model.*;
import com.firstProject.repository.UserAnswerRepositoryImpl;
import com.firstProject.userService.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userServiceClient.getUserAnswerByEmail(userAnswerRequest.getEmail());

        if (user != null && !checkIfUserAnsweredQuestionByUserIdAndQuestionId(user.getUserId(), userAnswerRequest.getQuestionOptionRequest().getQuestion().getId())) {
            boolean isRegistered = userServiceClient.getRegistered(); // נשתמש ישירות בערך במשתנה בוליאני

            if (isRegistered) {
                userAnswerRepository.createUserAnswer(userAnswerRequest.toUserAnswer(
                        user.getUserId(),
                        userAnswerRequest.getQuestionOptionRequest().getQuestion().getId(),
                        userAnswerRequest.getOption().getId()
                ));
            }
        }
    }

    @Override
    public void updateUserAnswer(UserAnswerRequest userAnswerRequest) {
        User user = userServiceClient.getUserAnswerByEmail(userAnswerRequest.getEmail());
        userAnswerRepository.updateUserAnswer(userAnswerRequest.toUserAnswer(
                user.getUserId(),
                userAnswerRequest.getOption().getQuestionId(),
                userAnswerRequest.getOption().getId()
        ));
    }

    @Override
    public void deleteUserAnswer(Long id) {
        userAnswerRepository.deleteUserAnswer(id);


    }

    @Override
    public void deleteQuestionAnswerByUserId(Long userId) {
        userAnswerRepository.deleteQuestionAnswerByUserId(userId);


    }

    @Override
    public Boolean checkIfUserAnsweredQuestionByUserIdAndQuestionId(Long userId, Long questionId) {
        return userAnswerRepository.checkIfUserAnsweredQuestionByUserIdAndQuestionId(userId, questionId);

    }

    @Override
    public Integer getUsersAnsweredCountByQuestionId(Long questionId) {
        return userAnswerRepository.getUsersAnsweredCountByQuestionId(questionId);
    }

    @Override
    public QuestionOptionSelectedResponse getUsersChoseQuestionOptionNumber(Long questionId) {
        List<OptionSelectedToMapper> answerChosenToMaps = userAnswerRepository.getUsersChoseQuestionOptionNumber(questionId);
        QuestionOptionResponse questions = questionService.getQuestionById(questionId);
        String questionText = questions.getQuestion().getTitle();
        List<OptionSelected> answers = new ArrayList<>();
        answers.add(new OptionSelected(questions.getOption1().getTextOption(), 0));
        answers.add(new OptionSelected(questions.getOption2().getTextOption(), 0));
        answers.add(new OptionSelected(questions.getOption3().getTextOption(), 0));
        answers.add(new OptionSelected(questions.getOption4().getTextOption(), 0));

        for (int i = 0; i < answerChosenToMaps.size(); i++) {
            Option option = optionService.getOptionById(answerChosenToMaps.get(i).getAnswerId());
            for (int x = 0; x < answers.size(); x++) {
                if (answers.get(x).getTextOption() == option.getTextOption()) {
                    answers.get(x).setSelectedAnswer(answerChosenToMaps.get(i).getCountingAnswers());
                }
            }
        }
        return new QuestionOptionSelectedResponse(questionText, answers);
    }

    @Override
    public Integer getNumberOfQuestionsUserAnswered(Long userId) {
        return userAnswerRepository.getNumberOfQuestionsUserAnswered(userId);
    }

    @Override
    public List<QuestionOptionSelectedResponse> getAllQuestionsAndAnswerSelectedCount() {
        List<QuestionOptionResponse> questions=questionService.getQuestionsList();
        List<QuestionOptionSelectedResponse> questionsResponse=new ArrayList<>();

        for (var question:questions) {
            try {
                QuestionOptionSelectedResponse response= getUsersChoseQuestionOptionNumber(question.getQuestion().getId());
                questionsResponse.add(response);
            }catch (Exception err){

            }
        }
        return questionsResponse;
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
}