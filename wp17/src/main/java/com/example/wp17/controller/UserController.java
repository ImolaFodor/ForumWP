package com.example.wp17.controller;

import com.example.wp17.model.Topic;
import com.example.wp17.model.User;
import com.example.wp17.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Imola on 6/2/2017.
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(
            method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        //System.out.println(subForumService.readSubForums());
        ArrayList<User> users= userService.readUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.GET, value="/mods")
    public ResponseEntity getModerators() {
        ArrayList<User> moderators= userService.getModerators();
        return new ResponseEntity(moderators, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        Boolean isFound= userService.addUser(user);
        //System.out.println(userService.readUsers());
        if(!isFound){
            return new ResponseEntity<>(isFound, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(isFound, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(
            method = RequestMethod.POST,value = "/login")
    public ResponseEntity<Boolean> loginCheck(@RequestBody User user) {
        Boolean isFound= userService.checkUser(user);
        
        //System.out.println(userService.readUsers());
        if(isFound){
            return new ResponseEntity<>(isFound, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(isFound, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(
            method = RequestMethod.DELETE,value = "/logout")
    public ResponseEntity deleteLogged() {
    	System.out.println("Izlogovace korisnika");
        userService.deleteLoggedUser();
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.GET,value = "/logged")
    public ResponseEntity<User> getLogged() {
        User u= userService.getLoggedUser();
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    
    @RequestMapping(
            method = RequestMethod.PUT,value = "/{username}/{role}")
    public ResponseEntity changeType(@PathVariable("username") String username,@PathVariable("role") String role) {
        
        userService.changeType(username,role);
        

        return new ResponseEntity(HttpStatus.OK);
    }
}
