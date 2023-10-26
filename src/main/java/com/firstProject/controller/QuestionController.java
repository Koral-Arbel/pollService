package com.firstProject.controller;

import com.firstProject.model.Question;
import com.firstProject.model.QuestionOptionRequest;
import com.firstProject.model.QuestionOptionResponse;
import com.firstProject.service.PollQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    PollQuestionService questionService;

    @PostMapping(value = "/create")
    public QuestionOptionResponse questionCreate(@RequestBody QuestionOptionRequest questionOptionRequest){
        return questionService.createQuestion(questionOptionRequest);
    }

    @PutMapping(value = "/{questionId}/update")
    public Question questionUpdate(@PathVariable Long questionId, @RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping(value = "/{questionId}/delete")
    public void questionDelete(@PathVariable Long questionId){
        questionService.deleteQuestion(questionId);
    }

    @GetMapping(value = "/{questionId}")
    public QuestionOptionResponse getQuestionById(@PathVariable Long questionId){
        return questionService.getQuestionById(questionId);
    }

    @GetMapping(value = "/allPoll")
    public List<QuestionOptionResponse> getAllPoll(){
        return questionService.getAllPoll();
    }
}