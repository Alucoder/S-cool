package com.aryan.scool.Models;

public class SubjectModel {
    private String subName;
    private String subjectDetail;
    private ClassModel classroom;
    private UserModel teacher;

    public SubjectModel(String subName, String subjectDetail, ClassModel classroom, UserModel teacher) {
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

    public ClassModel getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassModel classroom) {
        this.classroom = classroom;
    }

    public UserModel getTeacher() {
        return teacher;
    }

    public void setTeacher(UserModel teacher) {
        this.teacher = teacher;
    }
}
