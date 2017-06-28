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
    private enum type {
        TEXT,
        IMAGE,
        LINK
    }
    private String author;
    private String date;
    private String content;
    private List<String> comments= new ArrayList<String>();
    private int likesNo;
    private int dislikesNo;
    private String rateType;

    public Topic(String subForum, String name, String author, String date, List<String> comments, int likesNo, int dislikesNo) {
        this.subForum = subForum;
        this.name = name;
        this.author = author;
        this.date = date;
        this.comments = comments;
        this.likesNo = likesNo;
        this.dislikesNo = dislikesNo;
    }

    public Topic() {
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "subForum='" + subForum + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", comments=" + comments +
                ", likesNo=" + likesNo +
                ", dislikesNo=" + dislikesNo +
                '}';
    }
}
