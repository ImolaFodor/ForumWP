package com.example.wp17.service;

import com.example.wp17.model.Topic;
import com.example.wp17.model.User;

import org.springframework.stereotype.Service;

import java.awt.List;
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

    public void writeLoggedUser(User user){

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("loggeduser.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            System.out.println("Uloga korisnika koji hoce da se uloguje "+ user.getRole());
            out.writeObject(user);
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
    
    public ArrayList<User> getModerators(){

        ArrayList<User> users = null;
        ArrayList<User> moderators = new ArrayList();
        try {
            FileInputStream fileIn = new FileInputStream("users.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
            
            for(User u : users){
            	String moderator="MODERATOR";
            	if(u.getRole().equals(moderator)){
            		moderators.add(u);
            	}
            }
            
            in.close();
            fileIn.close();
            return moderators;
        }catch(Exception i) {
            i.printStackTrace();
            return new ArrayList<User>();
        }
    }

    public Boolean addUser(User user){
        Boolean isFound=false;
        ArrayList<User> users = readUsers();
        //System.out.println("korisnici bez dodatog:"+users+"hoce korisnik"+user);
        for(User u : users){
            if(u.getUsername().equals(user.getUsername())){
               isFound=true;
            }
        }

        if(!isFound){
            users.add(user);
        }

        writeUsers(users);
        //System.out.println("korisnici sa dodatim:"+users);

        return isFound;
    }

    public Boolean checkUser(User user){
        Boolean isFound=false;
        ArrayList<User> users = readUsers();
        //System.out.println("korisnici bez dodatog:"+users+"novi korisnik"+user);
        for(User u : users){
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                isFound=true;
                writeLoggedUser(u);
            }
        }
        return isFound;
    }
    
    public User getLoggedUser(){

        User u= new User();
        try {
            FileInputStream fileIn = new FileInputStream("loggeduser.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            u = (User) in.readObject();
            in.close();
            fileIn.close();
            return u;
        }catch(Exception i) {
            i.printStackTrace();
            return new User();
        }
    }
    
    public void deleteLoggedUser(){
    	User user= getLoggedUser();

    	PrintWriter writer;
		try {
			writer = new PrintWriter("loggeduser.ser");
			writer.print(" ");
	    	writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void changeType(String username, String role){
    	System.out.println("Nova uloga"+role);
    	ArrayList<User> users = readUsers();
    	User modifiedUser= new User();
    	for(User u : users){
    		if(u.getUsername().equals(username)){
    			u.setRole(role);
    			modifiedUser=u;
    			users.remove(u);
    			break;
    		}
    	}
    	
    	users.add(modifiedUser);
    	writeUsers(users);
    	
    }


}
