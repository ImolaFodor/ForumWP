package com.example.wp17.service;

import com.example.wp17.model.Comment;
import com.example.wp17.model.Message;
import com.example.wp17.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Imola on 6/30/2017.
 */
@Service
public class MessageService {
	
	@Autowired
	UserService userService;

    public void writeMessages(ArrayList<Message> messages){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("messages.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(messages);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }



    public ArrayList<Message> readMessages(){

        ArrayList<Message> messages = new ArrayList();
        ArrayList<Message> messagesByRecipient = new ArrayList();
        try {
            FileInputStream fileIn = new FileInputStream("messages.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            
            messages = (ArrayList<Message>) in.readObject();
            
            User logged= userService.getLoggedUser();
            
            for(Message m : messages){
            	System.out.println("Recipient of the message"+m.getRecipient());
            	System.out.println("Logged"+logged.getUsername());
            	if(m.getRecipient().equals(logged.getUsername())){
            		messagesByRecipient.add(m);
            	}
            }
            
            in.close();
            fileIn.close();
            return messagesByRecipient;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<Message>();
        }
    }
    
    public ArrayList<Message> readMessages(String logged){

        ArrayList<Message> messages = new ArrayList();
        ArrayList<Message> messagesByRecipient = new ArrayList();
        
        
        try {
            FileInputStream fileIn = new FileInputStream("messages.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            messages = (ArrayList<Message>) in.readObject();
            
            for(Message m : messages){
            	if(m.getRecipient().equals(logged)){
            		messagesByRecipient.add(m);
            	}
            }
            
            in.close();
            fileIn.close();
            return messagesByRecipient;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<Message>();
        }
    }

    public void addMessage(Message message){
        ArrayList<Message> messages = readMessages();
        message.setRead(false);

        messages.add(message);
        writeMessages(messages);
    }
    
    public void writeMessage(Message m){
        ArrayList<Message> messages= readMessages();
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("messages.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
                long dateId= new Date().getTime();
                m.setId(dateId);

            messages.add(m);
            out.writeObject(messages);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }
    
    public void editMessage(Message message){
        ArrayList<Message> messages= readMessages();
        Message editedMessage= new Message();

        for(Message m : messages){
            if(m.getId()==message.getId()) {
                editedMessage=m;
                messages.remove(m);
                editedMessage.setRead(true);
                break;
            }
        }
        writeMessages(messages);
        writeMessage(editedMessage);
    }
}
