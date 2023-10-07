package com.firstProject.service;

import com.firstProject.model.UserAnswer;
import com.firstProject.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService{
    @Autowired
    private OptionRepository optionRepository;


    @Override
    public UserAnswer createOption(UserAnswer userAnswer) {
        return null;
    }

    @Override
    public UserAnswer updateOption(UserAnswer userAnswer) {
        return null;
    }

    @Override
    public void deleteOptionFromAnswer(Long id) {

    }

    @Override
    public UserAnswer getOptionById(Long id) {
        return null;
    }

    @Override
    public List<UserAnswer> getOptionsByQuestionId(Long questionId) {
        return null;
    }
}
