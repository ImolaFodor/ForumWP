package com.example.wp17.service;

import com.example.wp17.model.Comment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Imola on 6/19/2017.
 */
@Service
public class CommentService {

    public void writeComments(ArrayList<Comment>comments){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("comments.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(comments);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }



    public ArrayList<Comment> readComments(String name){
        System.out.println("usao u servis commentsa");
        ArrayList<Comment> allComments = null;
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try {
            FileInputStream fileIn = new FileInputStream("comments.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allComments = (ArrayList<Comment>) in.readObject();
            //System.out.println(allTopics + "name of the subforum: "+name);
            for(Comment comment : allComments){
                System.out.println(comment.getTopic()+name);
                if(comment.getTopic().equals(name)){
                    System.out.println("nasao sa istim imenom");
                    comments.add(comment);
                }
            }
            in.close();
            fileIn.close();
            return comments;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<Comment>();
        }
    }
}