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
public class Answer {

    private int id;
    private String content;
    private int questionId;
    private boolean isSolution;
    private String explain;

    public Answer() {
    }

    public Answer(int id, String content, int questionId, boolean isSolution) {
        this.id = id;
        this.content = content;
        this.questionId = questionId;
        this.isSolution = isSolution;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean isIsSolution() {
        return isSolution;
    }

    public void setIsSolution(boolean isSolution) {
        this.isSolution = isSolution;
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", content=" + content + ", questionId=" + questionId + ", isSolution=" + isSolution + ", explain=" + getExplain() +'}';
    }

}
