/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author sannx
 */
public class Topic {

    private int id;
    private String name;
    private int order;
    private int courseId;
    private ArrayList<Lesson> lessonList = new ArrayList();
    private ArrayList<Quiz> quizList = new ArrayList();
    private quizFormat quizFormat;

    public Topic() {
    }

    public Topic(int id, String name, int order, int courseId) {
        this.id = id;
        this.name = name;
        this.order = order;
        this.courseId = courseId;
    }

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setLessonList(ArrayList<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public ArrayList<Lesson> getLessonList() {
        return lessonList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public quizFormat getQuizFormat() {
        return quizFormat;
    }

    public void setQuizFormat(quizFormat quizFormat) {
        this.quizFormat = quizFormat;
    }

    @Override
    public String toString() {
        return "Topic{" + "id=" + id + ", name=" + name + ", order=" + order + '}';
    }

}
