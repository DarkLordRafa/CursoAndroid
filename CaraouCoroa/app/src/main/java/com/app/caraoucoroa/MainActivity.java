package com.app.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

  Button playButton;
  Result result = new Result();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    playButton = findViewById(R.id.playButton);
  }

  public static int randomNumber(int min, int max){
    return min + new Random().nextInt(max - min +1);
  }

  public void playCoin(View v){
    final Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);

    int numberResult = randomNumber(1, 2);

    if (numberResult == 1){
      result.setResultTextString("Cara");
      result.setCoinImage(R.drawable.moeda_cara);
    }
    else if (numberResult == 2){
      result.setResultTextString("Coroa");
      result.setCoinImage(R.drawable.moeda_coroa);
    }

    resultIntent.putExtra("result", result);

    startActivity(resultIntent);
  }

}
