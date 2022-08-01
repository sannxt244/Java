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
public class Question {

    private int id;
    private String content;
    private int topicId;
    private boolean isActived;
    private ArrayList<Answer> answerList = new ArrayList<>();
    private User importBy ;

    public Question(int id, String content, int topicId, boolean isActived, User importBy) {
        this.id = id;
        this.content = content;
        this.topicId = topicId;
        this.isActived = isActived;
        this.importBy = importBy;
    }

    public User getImportBy() {
        return importBy;
    }

    public void setImportBy(User importBy) {
        this.importBy = importBy;
    }
    
    public Question() {
    }

    public Question(int id, String content, int topicId, boolean isActived) {
        this.id = id;
        this.content = content;
        this.topicId = topicId;
        this.isActived = isActived;
    }

    public Question(int id, String content, int topicId) {
        this.id = id;
        this.content = content;
        this.topicId = topicId;
    }

    public boolean isIsActived() {
        return isActived;
    }

    public void setIsActived(boolean isActived) {
        this.isActived = isActived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(ArrayList<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", content=" + content + ", topicId=" + topicId + '}';
    }

}
