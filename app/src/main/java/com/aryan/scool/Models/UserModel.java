package com.aryan.scool.Models;

import java.util.Date;
import java.util.List;

public class UserModel {

    String _id, fname, userid, classroom, password, email, profile, parentName, admin;
    Date dob;
    String phone;
    List<Achievements> achievements;


    public UserModel(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }


    public UserModel(List<Achievements> achievements) {
        this.achievements = achievements;
    }

    public UserModel(String _id, String name, String userid, String classroom, String password, String email, String profile, String parentName, String admin, Date dob, String phone) {
        this._id = _id;
        this.fname = name;
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

    public UserModel(String _id, String fname, String userid, String classroom, String password, String email, String profile, String parentName, String admin, Date dob, String phone, List<Achievements> achievements) {
        this._id = _id;
        this.fname = fname;
        this.userid = userid;
        this.classroom = classroom;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.parentName = parentName;
        this.admin = admin;
        this.dob = dob;
        this.phone = phone;
        this.achievements = achievements;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return fname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setName(String name) {
        this.fname = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Achievements> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievements> achievements) {
        this.achievements = achievements;
    }
}
