package com.example.wp17.service;

import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

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



    public ArrayList<Topic> readTopics(int id){

        ArrayList<Topic> allTopics = null;
        ArrayList<Topic> topics = null;
        try {
            FileInputStream fileIn = new FileInputStream("topics.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allTopics = (ArrayList<Topic>) in.readObject();

            for(Topic topic : allTopics){
                if(topic.getSubForum()==id){
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

    /*public void addTopic(Topic topic){
        ArrayList<Topic> topics = readTopics();
        topics.add(topic);
        writeTopics(topics);
    }*/


}


