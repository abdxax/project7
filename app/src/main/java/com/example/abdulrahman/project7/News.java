package com.example.abdulrahman.project7;

public class News {
    private String title;
    private String section;
    private String date;
    private String url;
    private String auth;


    public News(String title, String section, String date, String url, String auth) {
        this.title = title;
        this.section = section;
        this.date = date;
        this.url = url;
        this.auth=auth;
    }


    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }

    public String getAuth() {
        return auth;
    }
}
