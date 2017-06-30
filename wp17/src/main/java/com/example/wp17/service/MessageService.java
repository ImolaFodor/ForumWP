package com.example.wp17.service;

import com.example.wp17.model.Message;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Imola on 6/30/2017.
 */
@Service
public class MessageService {

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

        ArrayList<Message> messages = null;
        try {
            FileInputStream fileIn = new FileInputStream("messages.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            messages = (ArrayList<Message>) in.readObject();
            in.close();
            fileIn.close();
            return messages;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<Message>();
        }
    }

    public void addMessage(Message message){
        ArrayList<Message> messages = readMessages();
        message.setRecipient("Recipient");
        message.setSender("Sender");

        messages.add(message);
        writeMessages(messages);
    }
}
