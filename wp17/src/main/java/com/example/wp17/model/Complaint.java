package com.example.wp17.model;

import java.io.Serializable;

/**
 * Created by Imola on 6/30/2017.
 */
public class Complaint implements Serializable {
    private String complainer;
    private String date;
    private String author; //user koji je kreirao entitet
    private String content;
    private String name; //entiteta
    private int id; //komentara

    public Complaint(String complainer, String date, String author, String content, String name, int id) {
        this.complainer = complainer;
        this.date = date;
        this.author = author;
        this.content = content;
        this.name = name;
        this.id = id;
    }
    



	public Complaint() {
		// TODO Auto-generated constructor stub
	}


	public String getComplainer() {
        return complainer;
    }

    public void setComplainer(String complainer) {
        this.complainer = complainer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
