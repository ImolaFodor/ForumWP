package com.example.wp17.service;

import com.example.wp17.model.Comment;
import com.example.wp17.model.Complaint;
import com.example.wp17.model.Message;
import com.example.wp17.model.SubForum;
import com.example.wp17.model.Topic;
import com.example.wp17.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Imola on 6/30/2017.
 */

@Service
public class ComplaintService {
	
	@Autowired
	SubForumService subForumService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	UserService userService;
	
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
        
        String subforumS="subforum";
        String topicS="topic";
        String commentS="comment";
        if(complaint.getType().equals(subforumS)){
        	System.out.println("zalba je na "+complaint.getType());
        	
        	ArrayList<SubForum> subForums= subForumService.readSubForums();
        	
        	for(SubForum sf: subForums){
        		if(sf.getName().equals(complaint.getSubforum())){
        			complaint.setAuthor(sf.getResponsibleModerator());
        		}
        	}
        	
        	ArrayList<User> users= userService.readUsers();
        	for(User u : users){
        		String admin="ADMIN";
        		System.out.println("uloga korisnika"+u.getRole());
        		if(u.getRole().equals(admin)){
        			System.out.println("adminu se salje poruka");
        			Message m=new Message();
            		m.setRecipient(u.getUsername());
            		m.setRead(false);
            		m.setSender(complaint.getComplainer());
            		m.setContent(complaint.getContent());
            		
            		messageService.addMessage(m);
        		}
            		
            	
            }
        }else if(complaint.getType().equals(topicS)){
        	Topic topic= topicService.readTopic(complaint.getName());
        	System.out.println("zalba je na topic iz podforuma "+complaint.getSubforum());
        	ArrayList<SubForum> subForums= subForumService.readSubForums();
        	for(SubForum sf : subForums){
        		
            	if(sf.getName().equals(complaint.getSubforum())){
            		Message m=new Message();
            		m.setRecipient(sf.getResponsibleModerator());
            		m.setRead(false);
            		m.setSender(complaint.getComplainer());
            		m.setContent(complaint.getContent());
            		
            		messageService.addMessage(m);
            	}
            }
        	
        	ArrayList<User> users= userService.readUsers();
        	for(User u : users){
        		String admin="ADMIN";
        		if(u.getRole().equals(admin)){
        			Message m=new Message();
            		m.setRecipient(u.getUsername());
            		m.setRead(false);
            		m.setSender(complaint.getComplainer());
            		m.setContent(complaint.getContent());
            		
            		messageService.addMessage(m);
        		}
        	}
        	
        	complaint.setAuthor(topic.getAuthor());
        }else if(complaint.getType().equals(commentS)){
        	System.out.println("zalba je na "+complaint.getType());
        	ArrayList<Comment> comments= commentService.readComments(complaint.getName());
        	for(Comment c : comments){
            	if(c.getId()==complaint.getId()){
            		complaint.setAuthor(c.getAuthor().getUsername());
            	}
            }
        	
        	ArrayList<SubForum> subForums= subForumService.readSubForums();
        	for(SubForum sf : subForums){
        		
            	if(sf.getName().equals(complaint.getSubforum())){
            		Message m=new Message();
            		m.setRecipient(sf.getResponsibleModerator());
            		m.setRead(false);
            		m.setSender(complaint.getComplainer());
            		m.setContent(complaint.getContent());
            		
            		messageService.addMessage(m);
            	}
            }
        	
        	ArrayList<User> users= userService.readUsers();
        	for(User u : users){
        		String admin="ADMIN";
        		System.out.println("uloga korisnika"+u.getRole());
        		if(u.getRole().equals(admin)){
        			System.out.println("adminu se salje poruka");
        			Message m=new Message();
            		m.setRecipient(u.getUsername());
            		m.setRead(false);
            		m.setSender(complaint.getComplainer());
            		m.setContent(complaint.getContent());
            		
            		messageService.addMessage(m);
        		}
        	}
        }
        

        complaints.add(complaint);
        writeComplaints(complaints);
    }

}
