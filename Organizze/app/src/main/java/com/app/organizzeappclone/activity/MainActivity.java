package com.app.organizzeappclone.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.app.organizzeappclone.R;
import com.app.organizzeappclone.config.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;


public class MainActivity extends IntroActivity {

  private FirebaseAuth auth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Não pode usar esse setContent quando usa biblioteca de slides
    // pois ela substitui o layout padrão
    //setContentView(R.layout.activity_main);

    setButtonBackVisible(false);
    setButtonNextVisible(false);

    addSlide(new FragmentSlide.Builder()
        .background(android.R.color.white)
        .fragment(R.layout.screen_1)
        .build()
    );

    addSlide(new FragmentSlide.Builder()
        .background(android.R.color.white)
        .fragment(R.layout.screen_2)
        .build()
    );

    addSlide(new FragmentSlide.Builder()
        .background(android.R.color.white)
        .fragment(R.layout.screen_3)
        .build()
    );

    addSlide(new FragmentSlide.Builder()
        .background(android.R.color.white)
        .fragment(R.layout.screen_4)
        .build()
    );

    addSlide(new FragmentSlide.Builder()
        .background(android.R.color.white)
        .fragment(R.layout.login_choose_screen)
        .canGoForward(false)
        .build()
    );

  }

  public void btnSignUp(View v){
    startActivity(new Intent(this, SignUpActivity.class));
  }

  public void btnSignIn(View v){
    startActivity(new Intent(this, SignInActivity.class));
  }

  public void checkUser(){
    auth = FirebaseConfig.getAuth();
    //auth.signOut();

    if (auth.getCurrentUser() != null){
      openMainScreen();
    }
  }

  public void openMainScreen(){
    startActivity(new Intent(
        this,
        MainScreenActivity.class
    ));
  }

  @Override
  protected void onStart() {
    super.onStart();
    checkUser();
  }
}
