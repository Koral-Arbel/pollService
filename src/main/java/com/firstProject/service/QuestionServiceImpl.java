package com.firstProject.service;

import com.firstProject.model.PollOption;
import com.firstProject.model.PollQuestion;
import com.firstProject.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;



    @Override
    public String createQuestion(PollQuestion question, PollOption option) {
        if (question.getTitle() == null || option.getOption1() == null || option.getOption2() == null ||
                option.getOption3() == null || option.getOption4() == null) {
            throw new IllegalArgumentException("Question not created, title, first answer, second answer, third answer, and fourth answer are required");
        }
        // If all required fields are present, proceed with creating the question
        return String.valueOf(questionRepository.createQuestion(question));
    }

    @Override
    public List<PollQuestion> getAllQuestions() {
        return questionRepository.getQuestionsList();

    }

    @Override
    public PollQuestion getQuestionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Question ID cannot be null");
        }
        return questionRepository.getQuestionById(id);
    }

    @Override
    public PollQuestion updateQuestion(Long id, PollQuestion question) {
        PollQuestion existingQuestion = questionRepository.getQuestionById(id);
            if (existingQuestion != null) {
                existingQuestion.setTitle(question.getTitle());
                return questionRepository.updateQuestion(existingQuestion);
            }
            return null;
        }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.DeleteQuestion(id);
    }
}
