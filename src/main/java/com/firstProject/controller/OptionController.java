package com.firstProject.controller;

import com.firstProject.model.Option;
import com.firstProject.service.PollOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OptionController {
    @Autowired
    private PollOptionService optionService;

    @PostMapping(value = "/answer/create")
    public Option createAnswer(@RequestBody Option option){
        return optionService.createOption(option);
    }

    @PutMapping(value = "/answer/{answerId}/update")
    public Option updateOption(@RequestBody Option option, @PathVariable Long optionId){
        return optionService.updateOption(option);
    }

    @DeleteMapping(value = "/answer/{optionId}/delete")
    public void deleteOption(@PathVariable Long optionId){
        optionService.deleteOption(optionId);
    }

    @GetMapping(value = "/option/{optionId}")
    public Option getOptionById(@PathVariable Long optionId){
        return optionService.getOptionById(optionId);
    }

    @GetMapping(value = "/options/{questionId}")
    public List<Option> getOptionsByQuestionId(@PathVariable Long questionId){
        return optionService.getOptionsByQuestionId(questionId);
    }
}
