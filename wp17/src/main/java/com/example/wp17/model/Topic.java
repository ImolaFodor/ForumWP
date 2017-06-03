package com.example.wp17.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imola on 6/2/2017.
 */
public class Topic implements Serializable {
    private String subForum; //kom pripada
    private String name; //jedinstven u okviru podforuma
    //private Blob icon;
    private List<String> rules= new ArrayList<String>();
    private enum Coffee {
        TEXT,
        IMAGE,
        LINK
    }
    private String author;
    private String date;
    private List<String> comments= new ArrayList<String>();
    private int likesNo;
    private int dislikesNo;

    public String getSubForum() {
        return subForum;
    }

    public void setSubForum(String subForum) {
        this.subForum = subForum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public int getLikesNo() {
        return likesNo;
    }

    public void setLikesNo(int likesNo) {
        this.likesNo = likesNo;
    }

    public int getDislikesNo() {
        return dislikesNo;
    }

    public void setDislikesNo(int dislikesNo) {
        this.dislikesNo = dislikesNo;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "subForum='" + subForum + '\'' +
                ", name='" + name + '\'' +
                ", rules=" + rules +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", comments=" + comments +
                ", likesNo=" + likesNo +
                ", dislikesNo=" + dislikesNo +
                '}';
    }
}
