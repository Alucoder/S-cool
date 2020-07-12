package com.aryan.scool.Models;

public class ResultModel {
    String _id, examType, subject, classroom;
    int fullMarks, rank;
    float  marks;
    UserModel student;

    public ResultModel(String _id, String examType, String subject, String classroom, int fullMarks, int rank, float marks, UserModel student) {
        this._id = _id;
        this.examType = examType;
        this.subject = subject;
        this.classroom = classroom;
        this.fullMarks = fullMarks;
        this.rank = rank;
        this.marks = marks;
        this.student = student;
    }

    public ResultModel(String examType, String classroom, int fullMarks, int rank, float marks, UserModel student) {
        this.examType = examType;
        this.classroom = classroom;
        this.fullMarks = fullMarks;
        this.rank = rank;
        this.marks = marks;
        this.student = student;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(int fullMarks) {
        this.fullMarks = fullMarks;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getMarks() {
        return marks;
    }

    public void setMarks(float marks) {
        this.marks = marks;
    }

    public UserModel getStudent() {
        return student;
    }

    public void setStudent(UserModel student) {
        this.student = student;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
