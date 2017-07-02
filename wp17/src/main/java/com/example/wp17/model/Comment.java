package com.example.wp17.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imola on 6/2/2017.
 */
public class Comment implements Serializable {
    private long id;
    private String topic; //kojoj pripada
    private User author;
    private String date;
    private List<Comment> subComments= new ArrayList<Comment>();
    private Comment parentComment;
    private String content;
    private int likesNo;
    private int dislikesNo;
    private boolean edited;
    private String rateType;
    
    

    public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Comment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Comment> subComments) {
        this.subComments = subComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "topic='" + topic + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", subComments=" + subComments +
                ", parentComment=" + parentComment +
                ", content='" + content + '\'' +
                ", likesNo=" + likesNo +
                ", dislikesNo=" + dislikesNo +
                ", edited=" + edited +
                '}';
    }
}
