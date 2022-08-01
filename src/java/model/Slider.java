/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Minh Lee
 */
public class Slider {
    private int id;
    private String title;
    private String content;
    private String link;
    private String image_link;
    //

    public Slider() {
    }

    public Slider(int id, String title, String content, String link, String image_link) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.image_link = image_link;
    }
    //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
    
}
