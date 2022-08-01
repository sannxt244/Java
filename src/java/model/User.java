/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author dell
 */
public class User {

    private String fullName;
    private boolean gender;
    private String email;
    private String phoneNumber;
    private Role role;
    private String userName;
    private String password;
    private String avatar;
    private boolean verify;
    private Date registerDate;
    private int registerStatus;
    private String createdBy;

    public User() {
    }

    public User(Role role, String userName, Date registerDate, int registerStatus) {
        this.role = role;
        this.userName = userName;
        this.registerDate = registerDate;
        this.registerStatus = registerStatus;
    }

    public User(String avatar, String userName, Role role, Date registerDate, int registerStatus) {
        this.role = role;
        this.userName = userName;
        this.avatar = avatar;
        this.registerDate = registerDate;
        this.registerStatus = registerStatus;
    }

    public User(String fullName, boolean gender, String email, String phoneNumber, Role role, String userName, String password) {

        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public User(String fullName, boolean gender, String email, String phoneNumber, Role role, String userName, String password, String avatar, boolean verify, Date registerDate, int registerStatus) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.verify = verify;
        this.registerDate = registerDate;
        this.registerStatus = registerStatus;
    }

    public User(String fullName, boolean gender, String email, String phoneNumber, Role role, String userName, String password, String avatar, boolean verify) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.userName = userName;
        this.password = password;
        this.avatar = avatar;
        this.verify = verify;
    }

    public User(String userName, boolean gender, String email, String phoneNumber, String createdBy) {
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdBy = createdBy;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public int getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(int registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "User{" + "fullName=" + fullName + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", role=" + role + ", userName=" + userName + ", password=" + password + ", avatar=" + avatar + ", verify=" + verify + ", registerDate=" + registerDate + ", registerStatus=" + registerStatus + '}';
    }

}
