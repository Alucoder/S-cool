package com.aryan.scool.Models;

public class ResultViewModel {
    String _id, examType, classroom, student;
    int fullMarks, rank;
    float  marks;
    SubjectModel subject;

    public ResultViewModel(String _id, String examType, String classroom, String student, int fullMarks, int rank, float marks, SubjectModel subject) {
        this._id = _id;
        this.examType = examType;
        this.classroom = classroom;
        this.student = student;
        this.fullMarks = fullMarks;
        this.rank = rank;
        this.marks = marks;
        this.subject = subject;
    }

    public ResultViewModel(String examType, String classroom, String student, int fullMarks, int rank, float marks, SubjectModel subject) {
        this.examType = examType;
        this.classroom = classroom;
        this.student = student;
        this.fullMarks = fullMarks;
        this.rank = rank;
        this.marks = marks;
        this.subject = subject;
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

    public void setStudent(String student) {
        this.student = student;
    }

    public SubjectModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectModel subject) {
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

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
