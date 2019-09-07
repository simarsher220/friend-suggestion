package com.example.suggestion.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FriendRequests {

    private List<String> friendRequests;

    @JsonProperty("friend_requests")
    public List<String> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<String> friendRequests) {
        this.friendRequests = friendRequests;
    }
}
