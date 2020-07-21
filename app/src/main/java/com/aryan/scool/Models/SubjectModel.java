package com.aryan.scool.Models;

public class SubjectModel {
    private String subName;
    private String subjectDetail;
    private String classroom;
    private String teacher;

    public SubjectModel(String subName, String subjectDetail, String classroom, String teacher) {
        this.subName = subName;
        this.subjectDetail = subjectDetail;
        this.classroom = classroom;
        this.teacher = teacher;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubjectDetail() {
        return subjectDetail;
    }

    public void setSubjectDetail(String subjectDetail) {
        this.subjectDetail = subjectDetail;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
