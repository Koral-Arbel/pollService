package com.firstProject.service;

import com.firstProject.model.Option;
import com.firstProject.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollOptionServiceImpl implements PollOptionService {
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option createOption(Option option) {
        return getOptionById(optionRepository.createOption(option));
    }

    @Override
    public Option updateOption(Option option) {
        optionRepository.updateOption(option);
        return getOptionById(option.getId());
    }

    @Override
    public void deleteOption(Long id) {
        optionRepository.deleteOptionFromAnswer(id);
    }

    @Override
    public Option getOptionById(Long id) {
        return optionRepository.getOptionById(id);
    }

    @Override
    public List<Option> getOptionsByQuestionId(Long questionId) {
        return optionRepository.getOptionsByQuestionId(questionId);
    }
}