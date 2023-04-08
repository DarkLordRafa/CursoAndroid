package com.app.cardview.model;

public class Posts {

  private String userName;
  private String postDescription;
  private int postImage;

  public Posts() {
  }

  public Posts(String userName, String postDescription, int postImage) {
    this.userName = userName;
    this.postDescription = postDescription;
    this.postImage = postImage;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPostDescription() {
    return postDescription;
  }

  public void setPostDescription(String postDescription) {
    this.postDescription = postDescription;
  }

  public int getPostImage() {
    return postImage;
  }

  public void setPostImage(int postImage) {
    this.postImage = postImage;
  }
}
