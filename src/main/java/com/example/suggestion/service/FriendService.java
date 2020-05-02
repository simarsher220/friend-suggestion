package com.example.suggestion.service;

import com.example.suggestion.error.exception.CustomException;
import com.example.suggestion.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class FriendService {

    private static List<String> users = new ArrayList<>();
    private static Map<String, List<String>> graph = new HashMap<>();

    public List<String> getSuggestions(String userA) {
        isValidUser(userA);
        List<String> suggestions = new ArrayList<>();
        Map<String, Boolean> visited = new HashMap<>();
        for (String user : users) {
            visited.put(user, false);
        }
        dfs(userA, 4, visited, suggestions);
        return suggestions;
    }

    public List<String> getFriends(String userA) {
        isValidUser(userA);
        List<String> friends = new ArrayList<>();
        List<String> requests = graph.get(userA);
        if (requests == null) {
            throw new CustomException("no friends found!", HttpStatus.NOT_FOUND);
        }
        for (String request : requests) {
            List<String> requestsSentByOthers = graph.get(request);
            if (requestsSentByOthers == null) {
                continue;
            }
            if (requestsSentByOthers.contains(userA)) {
                friends.add(request);
            }
        }
        if (CollectionUtils.isEmpty(friends)) {
            throw new CustomException("no friends found!", HttpStatus.NOT_FOUND);
        }
        return friends;
    }

    public List<String> getFriendRequests(String userA) {
        isValidUser(userA);
        List<String> friendRequests = new ArrayList<>();
        List<String> requestsSentByMe = graph.get(userA);
        for (Map.Entry<String, List<String>> requestsSentByOthersPair : graph.entrySet()) {
            List<String> requestsSentByOthers = requestsSentByOthersPair.getValue();
            if (requestsSentByOthers.contains(userA) && (requestsSentByMe == null || !requestsSentByMe.contains(requestsSentByOthersPair.getKey()))) {
                friendRequests.add(requestsSentByOthersPair.getKey());
            }
        }
        if (CollectionUtils.isEmpty(friendRequests)) {
            throw new CustomException("no pending requests!", HttpStatus.NOT_FOUND);
        }
        return friendRequests;
    }

    public void addFriend(String userA, String userB) {
        isValidUser(userA);
        isValidUser(userB);
        if (graph.containsKey(userA)) {
            List<String> requests = graph.get(userA);
            if (requests.contains(userB)) {
                throw new CustomException("request already sent!", HttpStatus.BAD_REQUEST);
            }
            requests.add(userB);
            graph.replace(userA, requests);
        }
        else {
            List<String> requests = new ArrayList<>();
            requests.add(userB);
            graph.put(userA, requests);
        }
    }

    public void createUser(User user) {
        if (users.contains(user.getUsername())) {
            throw new CustomException("User already exists", HttpStatus.BAD_REQUEST);
        }
        users.add(user.getUsername());
    }

    private void dfs(String userA, Integer depth, Map<String, Boolean> visited, List<String> suggestions) {
        if (depth != 0) {
            visited.replace(userA, true);
            if (depth <= 2) {
                suggestions.add(userA);
            }
            List<String> friends = getFriends(userA);
            for (String friend : friends) {
                if (!visited.get(friend)) {
                    dfs(friend, depth-1, visited, suggestions);
                }
            }
        }
    }

    private void isValidUser(String user) {
        if (!users.contains(user)) {
            throw new CustomException("user doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }
}
