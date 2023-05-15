package com.app.organizzeappclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.organizzeappclone.R;
import com.app.organizzeappclone.config.FirebaseConfig;
import com.app.organizzeappclone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class SignInActivity extends AppCompatActivity {

  private EditText editTextSignInEmail, editTextSignInPassword;
  private Button buttonSignIn;
  private User user;
  private FirebaseAuth auth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);

    getSupportActionBar().setTitle("Login");

    editTextSignInEmail = findViewById(R.id.editTextSignInEmail);
    editTextSignInPassword = findViewById(R.id.editTextSignInPassword);
    buttonSignIn = findViewById(R.id.buttonSignIn);

    buttonSignIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        String textEmail = editTextSignInEmail.getText().toString();
        String textPassword = editTextSignInPassword.getText().toString();

        if (!textEmail.isEmpty()){
          if (!textPassword.isEmpty()){
            user = new User();
            user.setEmail(textEmail);
            user.setPassword(textPassword);
            signInUser();
          }
          else {
            Toast.makeText(
                getApplicationContext(),
                "Preencha a senha",
                Toast.LENGTH_SHORT
            ).show();
          }
        }
        else {
          Toast.makeText(
              getApplicationContext(),
              "Preencha o email",
              Toast.LENGTH_SHORT
          ).show();
        }
      }
    });
  }

  public void signInUser(){
    auth = FirebaseConfig.getAuth();
    auth.signInWithEmailAndPassword(
        user.getEmail(),
        user.getPassword()
    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()){

          openMainScreen();

        }
        else {
          String exception = "";
          try {
            throw task.getException();
          }
          catch (FirebaseAuthInvalidUserException e){
            exception = "Não existe uma conta com este email";
          }
          catch (FirebaseAuthInvalidCredentialsException e){
            exception = "Email ou senha incorretos!";
          }
          catch (Exception e){
            exception = "Erro ao fazer login: " + e.getMessage();
            e.printStackTrace();
          }

          Toast.makeText(
              getApplicationContext(),
              exception,
              Toast.LENGTH_SHORT
          ).show();
        }
      }
    });
  }

  public void openMainScreen(){
    startActivity(new Intent(
        this,
        MainScreenActivity.class
    ));
    finish();
  }

}
