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
public class Lesson extends Subject_Topic{
    private int id;
    private String name;
    private String video_link;
    private String html_content;
    private int subjectTopicId;
    private String type;

    public Lesson() {
    }

    public Lesson(int id, String name, String video_link, String html_content, int subjectTopicId, String type) {
        this.id = id;
        this.name = name;
        this.video_link = video_link;
        this.html_content = html_content;
        this.subjectTopicId = subjectTopicId;
        this.type = type;
    }

    public Lesson(int subject_id, String subject_name, int courseId, int order, int id, String name, String video_link, String html_content, int subjectTopicId, String type) {
        super(subject_id, subject_name, courseId, order);
        this.id = id;
        this.name = name;
        this.video_link = video_link;
        this.html_content = html_content;
        this.subjectTopicId = subjectTopicId;
        this.type = type;
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

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public String getHtml_content() {
        return html_content;
    }

    public void setHtml_content(String html_content) {
        this.html_content = html_content;
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
        return "Subject{" + "id=" + id + ", name=" + name + ", video_link=" 
                + video_link + ", html_content=" + html_content 
                + ", subjectTopicId=" + subjectTopicId + ", typo=" + type + '}';
    }
}
