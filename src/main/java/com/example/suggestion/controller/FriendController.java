package com.example.suggestion.controller;

import com.example.suggestion.mapper.FriendMapper;
import com.example.suggestion.model.*;
import com.example.suggestion.service.FriendService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FriendController {

    private FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiOperation("Add a user to our social media platform")
    public ResponseEntity createUser(@RequestBody User user) {
        friendService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/add/{userA}/{userB}", method = RequestMethod.POST)
    @ApiOperation("Send a friend request from userA (username) to userB (username)")
    public ResponseEntity addFriend(@PathVariable("userA") String userA, @PathVariable("userB") String userB) {
        friendService.addFriend(userA, userB);
        return new ResponseEntity<>(new SuccessResponse(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/friendRequests/{userA}", method = RequestMethod.GET)
    @ApiOperation("Get pending Friend Requests")
    public FriendRequests friendRequests(@PathVariable("userA") String userA) {
        return FriendMapper.getFriendRequests(friendService.getFriendRequests(userA));
    }

    @RequestMapping(value = "/friends/{userA}", method = RequestMethod.GET)
    @ApiOperation("Get all friends")
    public Friends friends(@PathVariable("userA") String userA) {
        return FriendMapper.getFriends(friendService.getFriends(userA));
    }

    @RequestMapping(value = "/suggestions/{userA}", method = RequestMethod.GET)
    @ApiOperation("Show friend suggestions")
    public Suggestions suggestions(@PathVariable("userA") String userA) {
        return FriendMapper.getSuggestions(friendService.getSuggestions(userA));
    }

}
