package com.example.wp17.controller;

import com.example.wp17.model.Comment;
import com.example.wp17.model.LoggedUser;
import com.example.wp17.model.Message;
import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import com.example.wp17.model.User;
import com.example.wp17.service.MessageService;
import com.example.wp17.service.SubForumService;
import com.example.wp17.service.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        messageService.addMessage(message);
        //System.out.println(subForumService.readSubForums());
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity getMessages() {
        //System.out.println(subForumService.readSubForums());
        ArrayList<Message> messages= messageService.readMessages();
        return new ResponseEntity(messages, HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.PUT)
public ResponseEntity editMessage(@RequestBody Message message) {

    messageService.editMessage(message);

    return new ResponseEntity(message, HttpStatus.OK);
}
}

