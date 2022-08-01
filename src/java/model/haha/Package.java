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
public class Package {
    private int id;
    String package_name;
    private int duration;

    public Package() {
    }

    public Package(int id, String package_name, int duration) {
        this.id = id;
        this.package_name = package_name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + id + ", package_name=" + package_name + ", duration=" + duration + '}';
    }
}
