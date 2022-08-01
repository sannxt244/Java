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
public class Answer {
    private int id;
    private String content;
    private int quesitonId;
    private boolean isSolution;
    
    //

    public Answer() {
    }

    public Answer(int id, String content, int quesitonId, boolean isSolution) {
        this.id = id;
        this.content = content;
        this.quesitonId = quesitonId;
        this.isSolution = isSolution;
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

    public int getQuesitonId() {
        return quesitonId;
    }

    public void setQuesitonId(int quesitonId) {
        this.quesitonId = quesitonId;
    }

    public boolean isIsSolution() {
        return isSolution;
    }

    public void setIsSolution(boolean isSolution) {
        this.isSolution = isSolution;
    }
    
}
