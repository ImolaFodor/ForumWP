package com.example.wp17.controller;

import com.example.wp17.model.Comment;
import com.example.wp17.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ArrayList<Comment> comments= commentService.readComments(name);
        /*for(Comment c: comments){
            System.out.println("ime teme iz liste tema "+c.getTopic());
        }*/

        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity writeComment(@RequestBody Comment comment) {

        commentService.writeComment(comment);

        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,value = "/rating")
    public ResponseEntity giveRating(@RequestBody Comment rateInfo) {
        if(rateInfo.getRateType().equals("like")){
            commentService.giveRating(rateInfo);
        }else{
            commentService.giveRating(rateInfo);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@RequestBody Comment comment) {

        commentService.deleteComment(comment);

        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @RequestMapping(
                method = RequestMethod.PUT)
    public ResponseEntity editComment(@RequestBody Comment comment) {

        commentService.editComment(comment);

        return new ResponseEntity(comment, HttpStatus.OK);
    }

}
