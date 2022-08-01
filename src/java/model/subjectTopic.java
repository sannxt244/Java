/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HDC
 */
public class subjectTopic {
    private int id;
    private String name;
    private int courseId;
    private int order;
    private String type;
    private List<Lesson> l= new ArrayList<>(); 
    private List<quizFormat> q= new ArrayList<>();

    public subjectTopic() {
    }

    public subjectTopic(int id, String name, int courseId, int order, String type) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.order = order;
        this.type = type;
    }

    public subjectTopic(String name, int courseId, int order, String type) {
        this.name = name;
        this.courseId = courseId;
        this.order = order;
        this.type = type;
    }

    public subjectTopic(String name, int order) {
        this.name = name;
        this.order = order;
    }

    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getOrder() {
        return order;
    }

    public String getType() {
        return type;
    }

    public List<Lesson> getL() {
        return l;
    }

    public List<quizFormat> getQ() {
        return q;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setL(List<Lesson> l) {
        this.l = l;
    }

    public void setQ(List<quizFormat> q) {
        this.q = q;
    }
    
    
}
