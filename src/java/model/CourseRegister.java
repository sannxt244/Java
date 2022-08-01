/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.sql.Date;

/**
 *
 * @author dell
 */
public class CourseRegister {
    private User user;
    private Course course;
    private Date dateRegister;
    private State state;
    private Package packagee;

    private int id;
    private int price;
    private String name_package;
    private int duration;


    public CourseRegister() {
    }


    public CourseRegister(User user, Course course, Date dateRegister, State state, Package packagee) {
        this.user = user;
        this.course = course;
        this.dateRegister = dateRegister;
        this.state = state;
        this.packagee = packagee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Package getPackagee() {
        return packagee;
    }

    public void setPackagee(Package packagee) {
        this.packagee = packagee;
    }
    

    public CourseRegister(int id, int price, String name_package, int duration) {
        this.id = id;
        this.price = price;
        this.name_package = name_package;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName_package() {
        return name_package;
    }

    public int getDuration() {
        return duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName_package(String name_package) {
        this.name_package = name_package;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    

}
