/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dell
 */
public class RevenuesByCategory {
    private Category category;
    private int revenues;

    public RevenuesByCategory() {
    }

    public RevenuesByCategory(Category category, int revenues) {
        this.category = category;
        this.revenues = revenues;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getRevenues() {
        return revenues;
    }

    public void setRevenues(int revenues) {
        this.revenues = revenues;
    }
    
    
}
