/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dai;

import java.util.Date;

/**
 *
 * @author HDC
 */
public class mycourse {
    private int id;
    private String name;
    private String date;
    private String package_name;
    private String state;

    public mycourse() {
    }

    public mycourse(int id, String name, String date, String package_name, String state) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.package_name = package_name;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getPackage_name() {
        return package_name;
    }

    public String getState() {
        return state;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
}
