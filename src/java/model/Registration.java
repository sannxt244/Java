package model;

import java.sql.Date;

public class Registration {

    private String username;
    private int courseId;
    private Date dateRegister;
    private int stateId;
    private int packageId;
    private String lastUpdateBy;

    public Registration() {
    }

    public Registration(String username, int courseId, int stateId, int packageId) {
        this.username = username;
        this.courseId = courseId;
        this.stateId = stateId;
        this.packageId = packageId;
    }

    public Registration(String username, int courseId, Date dateRegister, int stateId, int packageId) {
        this.username = username;
        this.courseId = courseId;
        this.dateRegister = dateRegister;
        this.stateId = stateId;
        this.packageId = packageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    @Override
    public String toString() {
        return "Registration{" + "username=" + username + ", courseId=" + courseId + ", dateRegister=" + dateRegister + ", stateId=" + stateId + ", packageId=" + packageId + '}';
    }
}
