package com.morlimoore.models;

public class Post {

    private int postID;
    private String message;
    private int userID;
    private int likes;
    private String createdAt;

    public Post() {

    }

    public Post(int postID, String message, int userID, int likes, String createdAt) {
        this.postID = postID;
        this.message = message;
        this.userID = userID;
        this.likes = likes;
        this.createdAt = createdAt;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", message='" + message + '\'' +
                ", userID=" + userID +
                ", likes=" + likes +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
