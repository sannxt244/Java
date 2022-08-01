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
public class pricePackage {
    private int courseId;
    private int packageld;
    private int price;
    private int priceSale;

    public pricePackage() {
    }

    public pricePackage(int courseId, int packageld, int price, int priceSale) {
        this.courseId = courseId;
        this.packageld = packageld;
        this.price = price;
        this.priceSale = priceSale;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getPackageld() {
        return packageld;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceSale() {
        return priceSale;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setPackageld(int packageld) {
        this.packageld = packageld;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }
    
    
}
