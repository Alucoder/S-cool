package com.aryan.scool.model;

import java.util.Date;

public class UserModel {

    String _id, name, userid, classroom, password, email, profile, parentName, admin;
    Date dob;
    long phone;

    public UserModel(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public UserModel(String _id, String name, String userid, String classroom, String password, String email, String profile, String parentName, String admin, Date dob, long phone) {
        this._id = _id;
        this.name = name;
        this.userid = userid;
        this.classroom = classroom;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.parentName = parentName;
        this.admin = admin;
        this.dob = dob;
        this.phone = phone;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }
}
