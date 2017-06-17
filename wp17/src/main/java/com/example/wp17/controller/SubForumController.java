package com.example.wp17.controller;

import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
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

/**
 * Created by Imola on 6/3/2017.
 */
@RestController
@RequestMapping("/api/forum")
public class SubForumController {

    @Autowired
    SubForumService subForumService;

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<SubForum> createItem(@RequestBody SubForum subforum) {
        subForumService.addSubForum(subforum);
        //System.out.println(subForumService.readSubForums());
        return new ResponseEntity<>(subforum, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity getSubForums() {
        //System.out.println(subForumService.readSubForums());
        ArrayList<SubForum> subforums= subForumService.readSubForums();
        return new ResponseEntity(subforums, HttpStatus.OK);
    }
}
