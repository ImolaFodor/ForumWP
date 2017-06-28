package com.example.wp17.service;

import com.example.wp17.model.SubForum;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

    /**
     * Created by Imola on 6/2/2017.
     */
    @Service
    public class SubForumService {

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
    }


