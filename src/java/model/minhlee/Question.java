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
public class Question {
    private int id;
    private String content;
    private int topicId;
    //

    public Question() {
    }

    public Question(int id, String content, int topicId) {
        this.id = id;
        this.content = content;
        this.topicId = topicId;
    }

    //

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
    
    
}
