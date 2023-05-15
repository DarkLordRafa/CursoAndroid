package com.app.organizzeappclone.model;

import com.app.organizzeappclone.config.FirebaseConfig;
import com.app.organizzeappclone.helper.Base64Custom;
import com.app.organizzeappclone.helper.DateCustom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Transaction {

  private String date, description, category, type;
  private Double value;
  private String id;

  public Transaction() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getValue() {
    return value;
  }

  public void setValue(Double value) {
    this.value = value;
  }

  public void saveTransaction(String date){
    FirebaseAuth auth = FirebaseConfig.getAuth();
    String userId = Base64Custom.code(auth.getCurrentUser().getEmail());
    String monthYear = DateCustom.date(date);

    DatabaseReference firebaseReference = FirebaseConfig.getFirebaseDatabase();
    firebaseReference.child("transaction")
        .child(userId)
        .child(monthYear)
        .push()
        .setValue(this);
  }
}
