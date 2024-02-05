package com.kazimasum.qrdemo;

public class Attendance {
    String date;
    String time;
    String subject;

    public Attendance(String date, String time, String subject) {
        this.date = date;
        this.time = time;
        this.subject = subject;
    }

    public Attendance() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
