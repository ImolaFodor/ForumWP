package com.example.wp17.service;

import com.example.wp17.model.SubForum;
import com.example.wp17.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    /**
     * Created by Imola on 6/2/2017.
     */
    @Service
    public class SubForumService {
    	
    	@Autowired
    	UserService userService;
    	
    	User foundUser= new User();

        public void writeSubForums(ArrayList<SubForum> subforums){

            try {
                FileOutputStream fileOut =
                        new FileOutputStream("subforums.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(subforums);
                out.close();
                fileOut.close();
                System.out.printf("saved");
            }catch(IOException i) {
                i.printStackTrace();
            }
        }



        public ArrayList<SubForum> readSubForums(){

            ArrayList<SubForum> subforums = null;
            try {
                FileInputStream fileIn = new FileInputStream("subforums.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                subforums = (ArrayList<SubForum>) in.readObject();
                in.close();
                fileIn.close();
                return subforums;
            }catch(Exception i) {
                i.printStackTrace();
                return new ArrayList<SubForum>();
            }
        }

        public void addSubForum(SubForum subforum){
            ArrayList<SubForum> subforums = readSubForums();
            subforums.add(subforum);
            writeSubForums(subforums);
        }

        public void deleteSubForum(String name){

            ArrayList<SubForum> subForums= readSubForums();

            for(SubForum sf : subForums){
                if(sf.getName().equals(name)){
                    subForums.remove(sf);
                    break;
                }
            }

            writeSubForums(subForums);

        }
        
        public void followSubForum(String name){
        	
        	
        	System.out.println("Da zaprati podforum" + name);
            ArrayList<SubForum> subForums= readSubForums();
            List<String> followedSubForums = new ArrayList();
            User logged = userService.getLoggedUser();
            System.out.println("Iz loggeduser fajla" + logged.getUsername());
            ArrayList<User> users= userService.readUsers();
            
            for(User user : users){
                if(user.getUsername().equals(logged.getUsername())){
                	foundUser= user;
                	followedSubForums= foundUser.getFollowedSubForums();
                	users.remove(user);
                    break;
                }
            }
            
            System.out.println("Korisnik koji je zapratio" + logged.getUsername());
            
            
            System.out.println("Forumi koje dosad prati" + foundUser.getFollowedSubForums());

            for(SubForum sf : subForums){
                if(sf.getName().equals(name)){
                	followedSubForums.add(sf.getName());
                	foundUser.setFollowedSubForums(followedSubForums);
                	
                    break;
                }
            }
            users.add(foundUser);
            System.out.println("Forumi koje prati sad" + followedSubForums);
            
            userService.writeUsers(users);
        }
    }


