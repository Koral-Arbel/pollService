package com.firstProject.model;

import java.util.List;

public class PollQuestion {
    private Long id;
    private String title;
    private List<String> options;

    public PollQuestion() {}

    public PollQuestion(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}