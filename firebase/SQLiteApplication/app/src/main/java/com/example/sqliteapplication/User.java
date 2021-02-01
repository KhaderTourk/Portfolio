package com.example.sqliteapplication;

public class User {

    public int ID ;
    public String UserID ;
    public String Username ;
    public String Password ;


    public void setID(int ID) {
        this.ID = ID;
    }
    public String getUserID() {
        return UserID;
    }
    public void setUserID(String userID) {
        UserID = userID;
    }
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        Username = username;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }


}
