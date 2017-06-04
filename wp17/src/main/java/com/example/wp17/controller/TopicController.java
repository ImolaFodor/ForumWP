package com.example.wp17.controller;

import com.example.wp17.model.Topic;
import com.example.wp17.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Imola on 6/3/2017.
 */
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping(
            method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity getTopics(@PathVariable("id") int id) {
        System.out.println(topicService.readTopics(id));
        ArrayList<Topic> topics= topicService.readTopics(id);
        return new ResponseEntity(topics, HttpStatus.OK);
    }
}
