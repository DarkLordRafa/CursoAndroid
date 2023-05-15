package com.app.organizzeappclone.model;


import com.app.organizzeappclone.config.FirebaseConfig;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class User {

  private String name;
  private String email;
  private String password;
  private String userId;
  private Double totalCost = 0.00;
  private Double totalGain = 0.00;

  public String getName() {
    return name;
  }

  @Exclude
  public String getUserId() {
    return userId;
  }

  public void save(){
    DatabaseReference firebase = FirebaseConfig.getFirebaseDatabase();
    firebase.child("users")
    .child(this.userId)
    .setValue(this);
  }

  public Double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(Double totalCost) {
    this.totalCost = totalCost;
  }

  public Double getTotalGain() {
    return totalGain;
  }

  public void setTotalGain(Double totalGain) {
    this.totalGain = totalGain;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }


  public User() {
  }


  public void setEmail(String email) {
    this.email = email;
  }

  @Exclude
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
