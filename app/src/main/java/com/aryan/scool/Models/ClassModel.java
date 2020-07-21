package com.aryan.scool.Models;

public class ClassModel {
    private String _id;
    private String classroom;
    private String section;

    public ClassModel(String _id, String classroom, String section) {
        this._id = _id;
        this.classroom = classroom;
        this.section = section;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
