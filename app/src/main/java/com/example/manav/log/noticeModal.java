package com.example.manav.log;

public class noticeModal {

private String date,content;

    public noticeModal() {

    }

    public noticeModal(String date, String content) {
        this.date = date;
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
