package com.aryan.scool.Models;

import java.util.Date;

public class AttendanceModel {
    String _id, student, attendanceBy, classroom;
    String date;
    Boolean status;

    public AttendanceModel(String student, Boolean status) {
        this.student = student;
        this.status = status;
    }

    public AttendanceModel(String _id, String student, String attendanceBy, String classroom, String date, Boolean status) {
        this._id = _id;
        this.student = student;
        this.attendanceBy = attendanceBy;
        this.classroom = classroom;
        this.date = date;
        this.status = status;
    }

    public AttendanceModel(String student, String attendanceBy, String classroom, String date, Boolean status) {
        this.student = student;
        this.attendanceBy = attendanceBy;
        this.classroom = classroom;
        this.date = date;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getAttendanceBy() {
        return attendanceBy;
    }

    public void setAttendanceBy(String attendanceBy) {
        this.attendanceBy = attendanceBy;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

