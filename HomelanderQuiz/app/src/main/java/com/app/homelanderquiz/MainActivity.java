package com.app.homelanderquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  MainClass mainClass = new MainClass();

  public void setMainClass(MainClass mainClass) {
    mainClass.homelanderMainImage = findViewById(R.id.mainImage);
    mainClass.question = findViewById(R.id.question);
    mainClass.status = findViewById(R.id.status);
    mainClass.option1 = findViewById(R.id.option1);
    mainClass.option2 = findViewById(R.id.option2);
    mainClass.option3 = findViewById(R.id.option3);
    mainClass.resultButtonLayout = findViewById(R.id.resultButtonLayout);
    mainClass.resultButton = findViewById(R.id.resultButton);
    mainClass.resultTextLayout = findViewById(R.id.resultTextLayout);
    mainClass.resultText = findViewById(R.id.resultText);
  }

  public void clickOption1(View v){
    setMainClass(mainClass);
    mainClass.clickOption1();
  }

  public void clickOption2(View v){
    setMainClass(mainClass);
    mainClass.clickOption2();
  }

  public void clickOption3(View v){
    setMainClass(mainClass);
    mainClass.clickOption3();
  }

  public void getResult(View v){
    setMainClass(mainClass);
    mainClass.displayResultLayout();
  }

}
