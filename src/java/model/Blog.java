package model;

import java.sql.Date;

public class Blog {

    private int id;
    private String title;
    private String content;
    private Date createdDate;
    private int categoryId;
    private boolean isActived;
    private String createdBy;
    private String image;

    public Blog() {
    }

    public Blog(int id, String title, String content, Date createdDate, int categoryId, boolean isActived, String createdBy, String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.categoryId = categoryId;
        this.isActived = isActived;
        this.createdBy = createdBy;
        this.image = image;
    }

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isIsActived() {
        return isActived;
    }

    public void setIsActived(boolean isActived) {
        this.isActived = isActived;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", title=" + title + ", content=" + content + ", createdDate=" + createdDate + ", categoryId=" + categoryId + ", isActived=" + isActived + ", createdBy=" + createdBy + ", image=" + image + '}';
    }

}
