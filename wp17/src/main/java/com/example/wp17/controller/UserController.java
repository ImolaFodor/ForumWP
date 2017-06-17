package com.example.wp17.controller;

import com.example.wp17.model.User;
import com.example.wp17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
