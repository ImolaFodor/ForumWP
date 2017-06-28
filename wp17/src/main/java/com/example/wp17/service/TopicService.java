package com.example.wp17.service;

import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Imola on 6/2/2017.
 */
@Service
public class TopicService {

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

        topic.setContent(t.getContent());
        topic.setAuthor("ulogovaniautor");
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


}


