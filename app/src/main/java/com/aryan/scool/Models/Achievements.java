package com.aryan.scool.Models;

public class Achievements {

    String _id, achievement, badge, remarks;

    public Achievements(String _id, String achievement, String badge, String remarks) {
        this._id = _id;
        this.achievement = achievement;
        this.badge = badge;
        this.remarks = remarks;
    }

    public Achievements(String achievement, String badge, String remarks) {
        this.achievement = achievement;
        this.badge = badge;
        this.remarks = remarks;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}
