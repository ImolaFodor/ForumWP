package com.example.wp17.service;

import com.example.wp17.model.Comment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            for(Comment c : comments){
                long dateId= new Date().getTime();
                c.setId(dateId);
                Date date=new Date();
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(date);
                c.setDate(s);
            }
            out.writeObject(comments);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void writeComment(Comment c){
        ArrayList<Comment> comments= readComments(c.getTopic());
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("comments.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                long dateId= new Date().getTime();
                c.setId(dateId);
                Date date=new Date();
                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                String s = formatter.format(date);
                c.setDate(s);

            comments.add(c);
            out.writeObject(comments);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public ArrayList<Comment> readComments(String name){
        //System.out.println("usao u servis commentsa");
        ArrayList<Comment> allComments = null;
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try {
            FileInputStream fileIn = new FileInputStream("comments.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            allComments = (ArrayList<Comment>) in.readObject();
            //System.out.println(allTopics + "name of the subforum: "+name);
            for(Comment comment : allComments){
                //System.out.println(comment.getTopic()+name);
                if(comment.getTopic().equals(name)){
                    //System.out.println("nasao sa istim imenom");
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

    public void giveRating(Comment rateInfo){
        ArrayList<Comment> comments= readComments(rateInfo.getTopic());

        Comment ratedComment= new Comment();
        for(Comment c : comments){
            if(c.getId()==rateInfo.getId() && rateInfo.getRateType().equals("like")){
                ratedComment=c;
                ratedComment.setLikesNo(c.getLikesNo()+1);
                comments.remove(c);
                break;
            }else{
                ratedComment=c;
                ratedComment.setDislikesNo(c.getDislikesNo()+1);
                comments.remove(c);
                break;
            }
        }

        comments.add(ratedComment);
        writeComments(comments);

    }

    public void deleteComment(Comment comment){
        ArrayList<Comment> comments= readComments(comment.getTopic());
        for(Comment c : comments){
            if(c.getId()==comment.getId()) {
                comments.remove(c);
                break;
            }
        }
        writeComments(comments);
    }

    public void editComment(Comment comment){
        ArrayList<Comment> comments= readComments(comment.getTopic());
        Comment editedComment= new Comment();

        for(Comment c : comments){
            if(c.getId()==comment.getId()) {
                editedComment=c;
                comments.remove(c);
                editedComment.setContent(comment.getContent());
                break;
            }
        }
        writeComments(comments);
        writeComment(editedComment);
    }
}
