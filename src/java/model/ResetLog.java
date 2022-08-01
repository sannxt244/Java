/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author dell
 */
public class ResetLog {
    private int id;
    private User user;
    private boolean changed;
    private Timestamp from;
    private Timestamp to;

    public ResetLog() {
    }

    public ResetLog(int id, User user, boolean changed, Timestamp from, Timestamp to) {
        this.id = id;
        this.user = user;
        this.changed = changed;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public Timestamp getFrom() {
        return from;
    }

    public void setFrom(Timestamp from) {
        this.from = from;
    }

    public Timestamp getTo() {
        return to;
    }

    public void setTo(Timestamp to) {
        this.to = to;
    }

   
    
    
}
