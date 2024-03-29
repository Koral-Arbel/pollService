package com.firstProject.controller;

import com.firstProject.model.Option;
import com.firstProject.service.PollOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/options")
public class OptionController {
    @Autowired
    private PollOptionService optionService;

    @PostMapping(value = "/create")
    public Option createAnswer(@RequestBody Option option){
        return optionService.createOption(option);
    }

    @PutMapping(value = "/{optionId}/update")
    public Option updateOption(@RequestBody Option option, @PathVariable Long optionId){
        return optionService.updateOption(option);
    }

    @DeleteMapping(value = "{optionId}/delete")
    public void deleteOption(@PathVariable Long optionId){
        optionService.deleteOption(optionId);
    }

    @GetMapping(value = "{optionId}")
    public Option getOptionById(@PathVariable Long optionId){
        return optionService.getOptionById(optionId);
    }

    @GetMapping(value = "/{questionId}")
    public List<Option> getOptionsByQuestionId(@PathVariable Long questionId){
        return optionService.getOptionsByQuestionId(questionId);
    }
}
