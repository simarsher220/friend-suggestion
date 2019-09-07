package com.example.suggestion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Suggestions {

    private List<String> suggestions;

    @JsonProperty("suggestions")
    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
}
