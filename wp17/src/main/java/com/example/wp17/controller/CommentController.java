package com.example.wp17.controller;

import com.example.wp17.model.Comment;
import com.example.wp17.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Imola on 6/3/2017.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(
            method = RequestMethod.GET,value = "/{name}")
    public ResponseEntity getComments(@PathVariable("name") String name) {
        System.out.println("usao u getComments na BE");
        ArrayList<Comment> comments= commentService.readComments(name);
        for(Comment c: comments){
            System.out.println("ime teme iz liste tema "+c.getTopic());
        }

        return new ResponseEntity(comments, HttpStatus.OK);
    }

}
