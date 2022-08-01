/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.minhlee;

/**
 *
 * @author Minh Lee
 */
public class Subject_Topic {
    private int id;
    private String name;
    private int courseId;
    private int order;
    //

    public Subject_Topic() {
    }

    public Subject_Topic(int id, String name, int courseId, int order) {
        this.id = id;
        this.name = name;
        this.courseId = courseId;
        this.order = order;
    }
    //

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}
