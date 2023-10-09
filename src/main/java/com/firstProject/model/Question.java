package com.firstProject.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private Long id;
    private String title;


    public Question(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionOptionResponse toQuestionOptionResponse(Question question, List<Option> options) {
        List<Option> currentOptions = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (options.size() > i) {
                currentOptions.add(options.get(i));
            } else {
                currentOptions.add(null);
            }
        }
        return new QuestionOptionResponse(
                question,
                currentOptions.get(0),
                currentOptions.get(1),
                currentOptions.get(2),
                currentOptions.get(3)
        );
    }
}