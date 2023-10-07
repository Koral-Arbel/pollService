package com.firstProject.controller;

import com.firstProject.model.PollOption;
import com.firstProject.model.PollQuestion;
import com.firstProject.model.QuestionOptionRequest;
import com.firstProject.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/create")
    public ResponseEntity<QuestionOptionRequest> createQuestion(@RequestBody QuestionOptionRequest questionOptionRequest) {
        PollQuestion question = questionOptionRequest.getQuestion();
        PollOption option = questionOptionRequest.getOption();

        // Validate your question and option data as needed

        String createdQuestion = questionService.createQuestion(question, option);
        return new ResponseEntity<QuestionOptionRequest>(questionOptionRequest, HttpStatus.CREATED);
    }

    @GetMapping(value = "/allQuestions")
    public ResponseEntity<List<PollQuestion>> getAllQuestions() {
        List<PollQuestion> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping(value = "/getQuestion/{id}")
    public ResponseEntity<PollQuestion> getQuestionById(@PathVariable Long id) {
        PollQuestion question = questionService.getQuestionById(id);
        return question != null ? ResponseEntity.ok(question) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody PollQuestion updatedQuestion) {
        PollQuestion question = questionService.updateQuestion(id, updatedQuestion);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }
}
