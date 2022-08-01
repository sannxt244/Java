/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.haha;

/**
 *
 * @author LAPTOP D&N
 */
public class Subject_Topic {
    private int subject_id;
    private String subject_name;
    private int courseId;
    private int order;

    public Subject_Topic() {
    }

    public Subject_Topic(int subject_id, String subject_name, int courseId, int order) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.courseId = courseId;
        this.order = order;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
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

    @Override
    public String toString() {
        return "Subject_Topic{" + "subject_id=" + subject_id + ", subject_name=" + subject_name + ", courseId=" + courseId + ", order=" + order + '}';
    }

}
