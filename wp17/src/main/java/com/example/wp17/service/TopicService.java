package com.example.wp17.service;

import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import com.example.wp17.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * Created by Imola on 6/2/2017.
 */
@Service
public class TopicService {
	
	@Autowired
	UserService userService;
	
	User foundUser= new User();
	
    public void writeTopics(ArrayList<Topic> topics){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("topics.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(topics);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }



    public ArrayList<Topic> readTopics(String name){
        ArrayList<Topic> allTopics = null;
        ArrayList<Topic> topics = new ArrayList<Topic>();
        try {
            FileInputStream fileIn = new FileInputStream("topics.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allTopics = (ArrayList<Topic>) in.readObject();
            //System.out.println(allTopics + "name of the subforum: "+name);
            for(Topic topic : allTopics){
                //System.out.println(topic.getSubForum()+name);
                if(topic.getSubForum().equals(name)){
                    //System.out.println("nasao sa istim imenom");
                    topics.add(topic);
                }
            }
            in.close();
            fileIn.close();
            return topics;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<Topic>();
        }
    }
    

    public Topic readTopic(String name){

        ArrayList<Topic> allTopics = null;
        ArrayList<Topic> topics = new ArrayList<Topic>();
        Topic topic= new Topic();
        try {
            FileInputStream fileIn = new FileInputStream("topics.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allTopics = (ArrayList<Topic>) in.readObject();
            //System.out.println(allTopics + "name: "+name);
            for(Topic t : allTopics){
                //System.out.println(t.getName());
                if(t.getName().equals(name)){
                    topic=t;
                }
            }
            in.close();
            fileIn.close();
            return topic;
        }catch(Exception i) {
            i.printStackTrace();
            return new Topic();
        }
    }

    /*public void addTopic(Topic topic){
        ArrayList<Topic> topics = readTopics();
        topics.add(topic);
        writeTopics(topics);
    }*/

    public void giveRating(Topic rateInfo){
            Topic topic= readTopic(rateInfo.getName());
            if(rateInfo.getRateType().equals("like")){
                int newLikeNo= topic.getLikesNo()+1;
                System.out.println(topic.getLikesNo());
                topic.setLikesNo(newLikeNo);
            }else{
                topic.setDislikesNo(topic.getDislikesNo()+1);
                //System.out.println("tema " + rateInfo.getName() + " koja pripada forumu " + rateInfo.getSubForum() + " je dobila dislike i ima " + topic.getDislikesNo() + "dislike-ova");
            }

            ArrayList<Topic> topics= readTopics(rateInfo.getSubForum());

            for(Topic t : topics){
                if(t.getName().equals(rateInfo.getName())){
                    topics.remove(t);
                    break;
                }
            }
            topics.add(topic);
            writeTopics(topics);
    }

    public void addTopic(Topic t){
        Topic topic= new Topic();

        System.out.println("Tema treba da se napravi za podforum " + t.getSubForum());
        topic.setType(t.getType());
        System.out.println("Tip topica: " + t.getType());
        
        System.out.println("URL slike " + t.getContent());
        
        String imageStr="IMAGE";
        
        if(t.getType().equals(imageStr)){
        	String onlyName= t.getContent().substring(12);
        	String onlyPath= "C:/Users/Imola/Documents/faks/3.godina/IMOLA/WP17/ForumWP/wp17/images/";
        	String imgURL= onlyPath.concat(onlyName);
        	System.out.println(imgURL);
        	topic.setContent(imgURL);
        }else{
        	topic.setContent(t.getContent());
        }
        
        
        
        topic.setAuthor(userService.getLoggedUser().getUsername());
        Date date=new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        topic.setDate(s);
        topic.setName(t.getName());
        topic.setSubForum(t.getSubForum());

        ArrayList<Topic> topics= readTopics(t.getSubForum());

        topics.add(topic);
        writeTopics(topics);
    }

    public void deleteTopic(Topic t){
        Topic topic= readTopic(t.getName());

        ArrayList<Topic> topics= readTopics(t.getSubForum());

        for(Topic ts : topics){
            if(ts.getName().equals(t.getName())){
                topics.remove(ts);
                break;
            }
        }

        writeTopics(topics);

    }

    public Topic editTopic(Topic t){
        Topic topic= readTopic(t.getName());

        topic.setContent(t.getContent());
        

        System.out.println("Tema koja se menja " + t.getName());
        Date date=new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date);
        topic.setDate(s);

        ArrayList<Topic> topics= readTopics(t.getSubForum());

        for(Topic ts : topics){
            if(ts.getName().equals(t.getName())){
                topics.remove(ts);
                break;
            }
        }

        topics.add(topic);
        writeTopics(topics);

        return topic;

    }
    
    public Topic saveTopic(Topic t){
        Topic topic= readTopic(t.getName());
        User logged = userService.getLoggedUser();
        System.out.println("Iz loggeduser fajla" + logged.getUsername());
        ArrayList<User> users= userService.readUsers();
        
        for(User user : users){
            if(user.getUsername().equals(logged.getUsername())){
            	List<String> savedTopics = user.getSavedTopics();
            	foundUser= user;
            	users.remove(user);
            	savedTopics.add(t.getName());
            	foundUser.setSavedTopics(savedTopics);
                break;
            }
        }
        
        users.add(foundUser);
        
        userService.writeUsers(users);
        
        System.out.println("Tema koja se cuva " + t.getName());

        /*ArrayList<Topic> topics= readTopics(t.getSubForum());

        for(Topic ts : topics){
            if(ts.getName().equals(t.getName())){
                topics.remove(ts);
                break;
            }
        }

        topics.add(topic);
        writeTopics(topics);*/ //cemu ovo
        

        return topic;

    }


}


