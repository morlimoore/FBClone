package com.morlimoore.models;

public class User {

    private int userID;
    private String fName;
    private String lName;
    private String email;
    private String hash;
    private String dateOfBirth;
    private String gender;
    private String createdAt;
    private int postID;

    public User(){}

    public User(String fName, String lName, String email, String hash, String dateOfBirth, String gender, String createdAt) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.hash = hash;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    public User(int userID, String fName, String lName, String email, String dateOfBirth, String gender, String createdAt) {
        this.userID = userID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    public User(String fName, String lName, int postID) {
        this.fName = fName;
        this.lName = lName;
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "User [fName=" + fName + ", lName=" + lName + ", email=" + email + ", hash=" + hash + ", dateOfBirth="
                + dateOfBirth + ", gender=" + gender + ", createdAt=" + createdAt + "]";
    }

}
