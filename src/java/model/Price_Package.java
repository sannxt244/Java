/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LAPTOP D&N
 */
public class Price_Package {
    int courseId;
    int packageId;
    int price;
    int priceSale;

    public Price_Package() {
    }

    public Price_Package(int courseId, int packageId, int price, int priceSale) {
        this.courseId = courseId;
        this.packageId = packageId;
        this.price = price;
        this.priceSale = priceSale;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
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

    @Override
    public String toString() {
        return "Price_Package{" + "courseId=" + courseId + ", packageId=" + packageId + ", price=" + price + ", priceSale=" + priceSale + '}';
    }
    
    
}