/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dai;

/**
 *
 * @author HDC
 */
public class slider {
    private int id;
    private String title;
    private String content;
    private String link;
    private String img_link;
    private boolean isActive;

    public slider() {
    }

    public slider(int id, String title, String content, String link, String img_link, boolean isActive) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.img_link = img_link;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getImg_link() {
        return img_link;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public slider(String title, String content, String link, String img_link, boolean isActive) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.img_link = img_link;
        this.isActive = isActive;
    }

    public slider(String title, String content, String link, String img_link) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.img_link = img_link;
    }

    
    
}
