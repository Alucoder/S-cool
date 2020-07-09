package com.aryan.scool.Models;

import java.util.Date;

public class NoticeModel {
    String _id, noticeType, title, desc, venue, classroom;
    Date date;

    public NoticeModel(String _id, String noticeType, String title, String desc, String venue, String classroom, Date date) {
        this._id = _id;
        this.noticeType = noticeType;
        this.title = title;
        this.desc = desc;
        this.venue = venue;
        this.classroom = classroom;
        this.date = date;
    }

    public NoticeModel(String noticeType, String title, String desc, String venue, String classroom, Date date) {
        this.noticeType = noticeType;
        this.title = title;
        this.desc = desc;
        this.venue = venue;
        this.classroom = classroom;
        this.date = date;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
