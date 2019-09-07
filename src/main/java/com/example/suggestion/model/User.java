package com.example.suggestion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String username;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
