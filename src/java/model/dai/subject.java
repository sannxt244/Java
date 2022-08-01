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
public class subject {
    private int id;
    private String name;
    private boolean isActive;
    private String created_by;
    private String description;
    private int categoryid;
    private String img;

    public subject() {
    }

    public subject(int id, String name, boolean isActive, String created_by, String description, int categoryid, String img) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.created_by = created_by;
        this.description = description;
        this.categoryid = categoryid;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public String getImg() {
        return img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public subject(String name, boolean isActive, String created_by, String description, int categoryid, String img) {
        this.name = name;
        this.isActive = isActive;
        this.created_by = created_by;
        this.description = description;
        this.categoryid = categoryid;
        this.img = img;
    }
    
    
}
