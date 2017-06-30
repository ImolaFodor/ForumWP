package com.example.wp17.service;

import com.example.wp17.model.Complaint;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Imola on 6/30/2017.
 */

@Service
public class ComplaintService {
    public void writeComplaints(ArrayList<Complaint> complaints){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("complaints.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(complaints);
            out.close();
            fileOut.close();
            System.out.printf("saved");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }



    public ArrayList<Complaint> readComplaints(){

        ArrayList<Complaint> complaints = null;
        try {
            FileInputStream fileIn = new FileInputStream("complaints.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            complaints = (ArrayList<Complaint>) in.readObject();
            in.close();
            fileIn.close();
            return complaints;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<Complaint>();
        }
    }

    public void addComplaint(Complaint complaint){
        ArrayList<Complaint> complaints = readComplaints();
        /*complaint.setRecipient("Recipient");
        complaint.setSender("Sender");*/

        complaints.add(complaint);
        writeComplaints(complaints);
    }

}
