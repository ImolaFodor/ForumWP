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
import java.util.Date;

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
	
	String subforumS="subforum";
    String topicS="topic";
    String commentS="comment";
	
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
        
        long dateId= new Date().getTime();
        complaint.setId(dateId);
        
        
        if(complaint.getType().equals(subforumS)){
        	
        	ArrayList<SubForum> subForums= subForumService.readSubForums();
        	
        	for(SubForum sf: subForums){
        		if(sf.getName().equals(complaint.getSubforum())){
        			complaint.setAuthor(sf.getResponsibleModerator());
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
            		m.setComplaintId(complaint.getId());
            		
            		messageService.addMessage(m);
        		}
            		
            	
            }
        }else if(complaint.getType().equals(topicS)){
        	Topic topic= topicService.readTopic(complaint.getName());
        	ArrayList<SubForum> subForums= subForumService.readSubForums();
        	for(SubForum sf : subForums){
        		
            	if(sf.getName().equals(complaint.getSubforum())){
            		Message m=new Message();
            		m.setRecipient(sf.getResponsibleModerator());
            		m.setRead(false);
            		m.setSender(complaint.getComplainer());
            		m.setContent(complaint.getContent());
            		m.setComplaintId(complaint.getId());
            		m.setSubject("Zalba");
            		
            		messageService.addMessage(m);
            	}
            }
        	
        	ArrayList<User> users= userService.readUsers();
        	for(User u : users){
        		String admin="ADMIN";
        		if(u.getRole().equals(admin)){
        			Message m2=new Message();
            		m2.setRecipient(u.getUsername());
            		m2.setRead(false);
            		m2.setSender(complaint.getComplainer());
            		m2.setContent(complaint.getContent());
            		m2.setComplaintId(complaint.getId());
            		m2.setSubject("Zalba");
            		
            		messageService.addMessage(m2);
        		}
        	}
        	
        	complaint.setAuthor(topic.getAuthor());
        }else if(complaint.getType().equals(commentS)){
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
            		m.setComplaintId(complaint.getId());
            		m.setSubject("Zalba");
            		
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
            		m.setComplaintId(complaint.getId());
            		
            		messageService.addMessage(m);
        		}
        	}
        }
        

        complaints.add(complaint);
        writeComplaints(complaints);
    }
    
    public void deleteComplaint(long cId){

        ArrayList<Complaint> complaints= readComplaints();

        for(Complaint c : complaints){
            if(c.getId()==cId){
                complaints.remove(c);
                
                Message m= new Message();
                m.setRecipient(c.getComplainer());
                m.setSender(userService.getLoggedUser().getUsername());
                m.setContent("Zalba Vam se ne uvazava!");
                m.setRead(false);
                m.setSubject("Tretman zalbe");
                
                messageService.addMessage(m);
                
                break;
            }
        }
        
        writeComplaints(complaints);
    }
    
    public void deleteEntity(long cId){

        ArrayList<Complaint> complaints= readComplaints();

        for(Complaint c : complaints){
            if(c.getId()==cId){
                complaints.remove(c);
                
                
                
                if(c.getType().equals(topicS)){
                	Topic t= topicService.readTopic(c.getName());                
                    topicService.deleteTopic(t);
                    
                    Message m= new Message();
                    m.setRecipient(c.getAuthor());
                    m.setSender(userService.getLoggedUser().getUsername());
                    String content="Brise Vam se tema "+ c.getName()+ "!";
                    m.setContent(content);
                    m.setRead(false);
                    m.setSubject("Tretman zalbe");
                    
                    messageService.addMessage(m);
                    
                }else{
                	ArrayList<SubForum> subForums= subForumService.readSubForums();
                	for(SubForum sf : subForums){
                		
                    	if(sf.getName().equals(c.getSubforum())){
                    		subForumService.deleteSubForum(c.getName());
                    	}
                    }
                	
                	Message m= new Message();
                    m.setRecipient(c.getAuthor());
                    m.setSender(userService.getLoggedUser().getUsername());
                    String content="Brise Vam se podforum "+ c.getName()+ "!";
                    m.setContent(content);
                    m.setRead(false);
                    m.setSubject("Tretman zalbe");
                    
                    messageService.addMessage(m);
                	
                }
                
                
                
                
                
                
                break;
            }
        }
        
        writeComplaints(complaints);
    }
    
    public void warnAuthor(long cId){

        ArrayList<Complaint> complaints= readComplaints();

        for(Complaint c : complaints){
            if(c.getId()==cId){
                complaints.remove(c);
                
                Message m= new Message();
                m.setRecipient(c.getComplainer());
                m.setSender(userService.getLoggedUser().getUsername());
                String content="Vasa zalba na "+ c.getName()+ " nije uvazena!";
                m.setContent(content);
                m.setRead(false);
                m.setSubject("Tretman zalbe");
                
                messageService.addMessage(m);
                
                if(c.getType().equals(topicS)){
                    
                    Message mt= new Message();
                    mt.setRecipient(c.getAuthor());
                    mt.setSender(userService.getLoggedUser().getUsername());
                    String contentT="Primili smo zalbu na Vasu temu "+ c.getName()+ "!";
                    mt.setContent(contentT);
                    mt.setRead(false);
                    mt.setSubject("Tretman zalbe");
                    
                    messageService.addMessage(mt);
                    
                }else{
                	ArrayList<SubForum> subForums= subForumService.readSubForums();
                	for(SubForum sf : subForums){
                		
                    	if(sf.getName().equals(c.getSubforum())){
                    		subForumService.deleteSubForum(c.getName());
                    	}
                    }
                	
                	Message mSF= new Message();
                    mSF.setRecipient(c.getAuthor());
                    mSF.setSender(userService.getLoggedUser().getUsername());
                    String contentSF="Primili smo zalbu na Vas podforum "+ c.getName()+ "!";
                    mSF.setContent(contentSF);
                    mSF.setRead(false);
                    mSF.setSubject("Tretman zalbe");
                    
                    messageService.addMessage(mSF);
                	
                }
                
                
                
                
                
                
                break;
            }
        }
        
        writeComplaints(complaints);
    }
    
}
