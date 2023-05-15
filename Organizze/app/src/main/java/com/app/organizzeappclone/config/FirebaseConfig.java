package com.app.organizzeappclone.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {

  private static FirebaseAuth auth;
  private static DatabaseReference firebase;

  public static FirebaseAuth getAuth(){

    if (auth == null){
      auth = FirebaseAuth.getInstance();
    }
    return auth;
  }

  public static DatabaseReference getFirebaseDatabase(){
    if (firebase == null){
      firebase = FirebaseDatabase.getInstance().getReference();
    }
    return firebase;
  }

}
