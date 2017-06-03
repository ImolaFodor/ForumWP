package com.example.wp17.service;

import com.example.wp17.model.User;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Imola on 6/2/2017.
 */
@Service
public class UserService {

    public void writeUsers(ArrayList<User> users){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("users.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }



    public ArrayList<User> readUsers(){

        ArrayList<User> users = null;
        try {
            FileInputStream fileIn = new FileInputStream("users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
            return users;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<User>();
        }
    }

    public void addUser(User user){
        ArrayList<User> users = readUsers();
        users.add(user);
        writeUsers(users);
    }


}
