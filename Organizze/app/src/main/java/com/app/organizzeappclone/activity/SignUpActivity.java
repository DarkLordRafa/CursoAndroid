package com.app.organizzeappclone.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.organizzeappclone.R;
import com.app.organizzeappclone.config.FirebaseConfig;
import com.app.organizzeappclone.helper.Base64Custom;
import com.app.organizzeappclone.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;


public class SignUpActivity extends AppCompatActivity {

  private EditText editTextSignUpName, editTextSignUpEmail, editTextSignUpPassword;
  private Button buttonSignUp;
  private FirebaseAuth auth;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);

    getSupportActionBar().setTitle("Cadastro");

    editTextSignUpName = findViewById(R.id.editTextSignUpName);
    editTextSignUpEmail = findViewById(R.id.editTextSignUpEmail);
    editTextSignUpPassword = findViewById(R.id.editTextSignUpPassword);
    buttonSignUp = findViewById(R.id.buttonSignUp);

    buttonSignUp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String textName = editTextSignUpName.getText().toString();
        String textEmail = editTextSignUpEmail.getText().toString();
        String textPassword = editTextSignUpPassword.getText().toString();

        if (!textName.isEmpty()){
          if (!textEmail.isEmpty()){
            if (!textPassword.isEmpty()){
              user = new User();
              user.setName(textName);
              user.setEmail(textEmail);
              user.setPassword(textPassword);
              signUpUser();
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
        else {
          Toast.makeText(
              getApplicationContext(),
              "Preencha o nome",
              Toast.LENGTH_SHORT
          ).show();
        }

      }
    });

  }

  public void signUpUser(){
    auth = FirebaseConfig.getAuth();

    auth.createUserWithEmailAndPassword(
        user.getEmail(),
        user.getPassword()
    ).addOnCompleteListener(
        this,
        new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){

              String userId = Base64Custom.code(user.getEmail());
              user.setUserId(userId);
              user.save();
              finish();

            }
            else {

              String exception = "";
              try {
                throw task.getException();
              }
              catch (FirebaseAuthWeakPasswordException e){
                exception = "Digite uma senha mais forte";
              }
              catch (FirebaseAuthInvalidCredentialsException e){
                exception = "Digite um email válido";
              }
              catch (FirebaseAuthUserCollisionException e){
                exception = "Este email já foi cadastrado!";
              }
              catch (Exception e){
                exception = "Erro ao cadastrar o usuário: " + e.getMessage();
                e.printStackTrace();
              }

              Toast.makeText(
                  getApplicationContext(),
                  exception,
                  Toast.LENGTH_SHORT
              ).show();
            }
          }
        }
    );
  }

}
