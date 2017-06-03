package com.example.wp17.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imola on 6/2/2017.
 */
public class SubForum implements Serializable {
    private int id;
    private String name;
    private String description;
    //private Blob icon;
    private List<String> rules= new ArrayList<String>();
    private String responsibleModerator;
    private List<String> moderators= new ArrayList<String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getResponsibleModerator() {
        return responsibleModerator;
    }

    public void setResponsibleModerator(String responsibleModerator) {
        this.responsibleModerator = responsibleModerator;
    }

    public List<String> getModerators() {
        return moderators;
    }

    public void setModerators(List<String> moderators) {
        this.moderators = moderators;
    }

    @Override
    public String toString() {
        return "SubForum{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rules=" + rules +
                ", responsibleModerator='" + responsibleModerator + '\'' +
                ", moderators=" + moderators +
                '}';
    }
}
