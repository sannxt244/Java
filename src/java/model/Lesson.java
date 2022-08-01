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
public class Lesson {

    private int id;
    private String name;
    private String videoLink;
    private String htmlContent;
    private int subjectTopicId;
    private String type;
    private int order;
    
    public Lesson() {
    }
    
    public Lesson(int id, String name, String video_link, String html_content, int subjectTopicId, String type, int order) {
        this.id = id;
        this.name = name;
        this.videoLink = video_link;
        this.htmlContent = html_content;
        this.subjectTopicId = subjectTopicId;
        this.type = type;
        this.order = order;
    }

    public Lesson(String name, String videoLink, String htmlContent, int subjectTopicId, String type, int order) {
        this.name = name;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
        this.subjectTopicId = subjectTopicId;
        this.type = type;
        this.order = order;
    }

    public Lesson(String name, String videoLink, String htmlContent, String type, int order) {
        this.name = name;
        this.videoLink = videoLink;
        this.htmlContent = htmlContent;
        this.type = type;
        this.order = order;
    }



    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public int getSubjectTopicId() {
        return subjectTopicId;
    }

    public void setSubjectTopicId(int subjectTopicId) {
        this.subjectTopicId = subjectTopicId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", name=" + name + ", videoLink=" + videoLink + ", htmlContent=" + htmlContent + ", subjectTopicId=" + subjectTopicId + ", type=" + type + '}';
    }

}
