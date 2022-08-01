/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author sannx
 */
public class Quiz {

    private int id;
    private int subjectTopicId;
    private String name;
    private int duration;
    private String description;
    private int number;
    private String type;

    public Quiz() {
    }

    public Quiz(int id, int subjectTopicId, String name, int duration, String description, int number, String type) {
        this.id = id;
        this.subjectTopicId = subjectTopicId;
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.number = number;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectTopicId() {
        return subjectTopicId;
    }

    public void setSubjectTopicId(int subjectTopicId) {
        this.subjectTopicId = subjectTopicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", subjectTopicId=" + subjectTopicId + ", name=" + name + ", duration=" + duration + ", description=" + description + ", number=" + number + ", type=" + type + '}';
    }

}
