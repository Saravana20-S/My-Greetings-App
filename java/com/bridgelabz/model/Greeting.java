package com.bridgelabz.model;

public class Greeting {

    private int id;
    private String userName;
    private String message;
    private String createdDate;

    public Greeting() {
    }

    public Greeting(int id, String userName,
                    String message, String createdDate) {
        this.id = id;
        this.userName = userName;
        this.message = message;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}