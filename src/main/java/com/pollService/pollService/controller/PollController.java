package com.pollService.pollService.controller;

import com.pollService.pollService.model.QuestionRequest;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.HashMap;

@RestController
public class PollController {

    private HashMap<Integer, QuestionRequest> poolMap = new HashMap<>();


    @GetMapping(value = "/question/{UniqueQuestionId}/title/")
    public String getQuestionById(@PathVariable Integer UniqueQuestionId,
                                  @PathParam(value = "questionsId") String theQuestionTitle) throws Exception {
        return "You requested Unique question id " + UniqueQuestionId + " Add the question title " + theQuestionTitle;
    }

    @PostMapping(value = "question/createQuestion")
    public QuestionRequest createQuestion(@RequestBody QuestionRequest questionRequest) {
        poolMap.put(questionRequest.getQuestionId(),questionRequest);
        return questionRequest;
    }

    @PutMapping(value = "question/{UniqueQuestionId}/updateQuestion")
    public QuestionRequest updateQuestion(
            @PathVariable Integer UniqueQuestionId,
            @RequestBody QuestionRequest questionRequest) {
        if (poolMap.containsKey(UniqueQuestionId)){
            poolMap.put(questionRequest.getQuestionId(), questionRequest);
        }
        return questionRequest;
    }

    @DeleteMapping(value = "question/{questionId}/delete")
    public String deleteQuestionById(@PathVariable Integer questionId){
        if(poolMap.containsKey(questionId)){
            poolMap.remove(questionId);
            return  "Successfully deleted question with id " + questionId;
        } else {
            return "Not existing question with id " + questionId;
        }
    }

    @GetMapping(value = "question/{questionId}")
    public QuestionRequest getQuestionById(@PathVariable Integer questionId){
        return poolMap.get(questionId);
    }

}
