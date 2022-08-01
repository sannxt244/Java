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

public class Course {
    int id;
    String name;
    int isActive;
    String created_by;
    String description;
    int categoryId;
    String img;
    int price;
    int priceSale;
    String package_name;
    int packageId;

    public Course() {
    }

    public Course(int id, String name, int isActive, String created_by, String description, int categoryId, String img) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.created_by = created_by;
        this.description = description;
        this.categoryId = categoryId;
        this.img = img;
    }
    
    

    public Course(int id, String name, int isActive, String created_by, String description, int categoryId,String img , int price, int priceSale, String package_name, int packageId) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.created_by = created_by;
        this.description = description;
        this.categoryId = categoryId;
        this.img = img;
        this.price = price;
        this.priceSale = priceSale;
        this.package_name = package_name;
        this.packageId = packageId;
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

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", name=" + name + ", isActive=" + isActive + ", created_by=" + created_by + ", description=" + description + ", categoryId=" + categoryId + ", img=" + img + ", price=" + price + ", priceSale=" + priceSale + ", package_name=" + package_name + ", packageId=" + packageId + '}';
    }

}

