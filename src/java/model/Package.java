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
public class Package {
    int id;
    String name;
    String duration;
    Price_Package price;
    Price_Package priceSale;

    public Package() {
    }

    public Package(int id, String name, String duration, Price_Package price, Price_Package priceSale) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.priceSale = priceSale;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Price_Package getPrice() {
        return price;
    }

    public void setPrice(Price_Package price) {
        this.price = price;
    }

    public Price_Package getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(Price_Package priceSale) {
        this.priceSale = priceSale;
    }

    @Override
    public String toString() {
        return "Package{" + "id=" + id + ", name=" + name + ", duration=" + duration + ", price=" + price + ", priceSale=" + priceSale + '}';
    }
    
}
