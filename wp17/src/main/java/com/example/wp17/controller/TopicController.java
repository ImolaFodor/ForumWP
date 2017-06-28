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
        //System.out.println("usao u getTopics na BE");
        ArrayList<Topic> topics= topicService.readTopics(name);
        /*for(Topic t: topics){
            System.out.println("ime teme iz liste tema "+t.getName());
        }*/

        return new ResponseEntity(topics, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,value = "/topicdetail/{subforum}/{name}")
    public ResponseEntity getTopic(@PathVariable("name") String name, @PathVariable("subforum") String subForum) {
       //System.out.println("Iz kontrolera: "+ topicService.readTopic(name));
        Topic topic= topicService.readTopic(name);
        return new ResponseEntity(topic, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,value = "/rating")
    public ResponseEntity giveRating(@RequestBody Topic rateInfo) {
        if(rateInfo.getRateType().equals("like")){
            System.out.println("tema " + rateInfo.getName() + " koja pripada forumu " + rateInfo.getSubForum() + " je dobila like");
            topicService.giveRating(rateInfo);
        }else{
            topicService.giveRating(rateInfo);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,value = "/newtopic")
    public ResponseEntity addTopic(@RequestBody Topic topic) {

            topicService.addTopic(topic);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.PUT)
    public ResponseEntity editTopic(@RequestBody Topic t) {
            Topic topic= topicService.editTopic(t);

        return new ResponseEntity(topic,HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public ResponseEntity deleteTopic(@RequestBody Topic t) {
        topicService.deleteTopic(t);

        return new ResponseEntity(HttpStatus.OK);
    }
}
