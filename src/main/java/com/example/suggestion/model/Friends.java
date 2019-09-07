package com.example.suggestion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Friends {

    private List<String> friends;

    @JsonProperty("friends")
    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
