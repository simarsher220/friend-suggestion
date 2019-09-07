package com.example.suggestion.mapper;

import com.example.suggestion.model.FriendRequests;
import com.example.suggestion.model.Friends;
import com.example.suggestion.model.Suggestions;

import java.util.List;

public class FriendMapper {


    public static Suggestions getSuggestions(List<String> suggestionsList) {
        Suggestions suggestions = new Suggestions();
        suggestions.setSuggestions(suggestionsList);
        return suggestions;
    }

    public static Friends getFriends(List<String> friendList) {
        Friends friends = new Friends();
        friends.setFriends(friendList);
        return friends;
    }

    public static FriendRequests getFriendRequests(List<String> friendRequestList) {
        FriendRequests friendRequests = new FriendRequests();
        friendRequests.setFriendRequests(friendRequestList);
        return friendRequests;
    }
}
