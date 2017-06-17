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
            method = RequestMethod.GET,value = "/{name}")
    public ResponseEntity getTopics(@PathVariable("name") String name) {
        System.out.println("usao u getTopics na BE");
        ArrayList<Topic> topics= topicService.readTopics(name);
        for(Topic t: topics){
            System.out.println("ime teme iz liste tema "+t.getName());
        }

        return new ResponseEntity(topics, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,value = "/topicdetail/{name}")
    public ResponseEntity getTopic(@PathVariable("name") String name) {
       //System.out.println("Iz kontrolera: "+ topicService.readTopic(name));
        Topic topic= topicService.readTopic(name);
        return new ResponseEntity(topic, HttpStatus.OK);
    }
}
