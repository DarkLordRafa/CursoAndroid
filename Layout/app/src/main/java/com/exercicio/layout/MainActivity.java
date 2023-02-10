package com.exercicio.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void changeText(View view){
    TextView text3 = findViewById(R.id.text3);
    Integer randomNumber = new Random().nextInt(2);
    String newText = "Novo texto";
    String newText2 = "Segundo texto";
    if (randomNumber == 0){
      text3.setText(newText);
    }
    else{
      text3.setText(newText2);
    }
  }
}
