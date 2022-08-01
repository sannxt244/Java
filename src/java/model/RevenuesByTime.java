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
public class RevenuesByTime {
   private String time;
   private int revenues;

    public RevenuesByTime() {
    }

    public RevenuesByTime(String time, int revenues) {
        this.time = time;
        this.revenues = revenues;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRevenues() {
        return revenues;
    }

    public void setRevenues(int revenues) {
        this.revenues = revenues;
    }
   
}
