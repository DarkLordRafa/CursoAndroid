package com.app.homelanderquiz;


import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainClass extends AppCompatActivity {

  Texts texts = new Texts();

  public ImageView homelanderMainImage;
  public TextView question;
  public TextView status;
  public Button option1;
  public Button option2;
  public Button option3;
  public View resultButtonLayout;
  public TextView resultButton;
  public View resultTextLayout;
  public TextView resultText;
  public TextView restartButton;



  private int currentQuestion = 0;

  /*
    Resultado final e texto do status de acordo com a reputação
    (Se ficar muito baixa o jogo acaba com final ruim)
  */
  private int reputation = 0;




  public void changeElements(
      int src,
      String question,
      String status,
      String option1,
      String option2,
      String option3
      ){
    if(this.currentQuestion <= 10) {
      homelanderMainImage.setImageResource(src);
      this.question.setText(question);
      this.status.setText(status);
      this.option1.setText(option1);
      this.option2.setText(option2);
      this.option3.setText(option3);
    }
    else{
      displayButtonLayout();
    }
  }


  public void showResult(int choise){
    this.currentQuestion++;

      if (currentQuestion == 1 && choise == 1) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[0],
            texts.statusTexts[1],
            texts.button1Texts[0],
            texts.button2Texts[0],
            texts.button3Texts[0]
        );
      } else if (currentQuestion == 1 && choise == 2) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_angry,
            texts.question1Texts[0],
            texts.statusTexts[5],
            texts.button1Texts[0],
            texts.button2Texts[0],
            texts.button3Texts[0]
        );
      } else if (currentQuestion == 1 && choise == 3) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_normal,
            texts.question1Texts[0],
            texts.statusTexts[3],
            texts.button1Texts[0],
            texts.button2Texts[0],
            texts.button3Texts[0]
        );
      } else if (currentQuestion == 2 && choise == 1) {
        this.reputation -= 2;
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[1],
            texts.statusTexts[6],
            texts.button1Texts[1],
            texts.button2Texts[1],
            texts.button3Texts[1]
        );
      } else if (currentQuestion == 2 && choise == 2) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_normal,
            texts.question1Texts[1],
            texts.statusTexts[0],
            texts.button1Texts[1],
            texts.button2Texts[1],
            texts.button3Texts[1]
        );
      } else if (currentQuestion == 2 && choise == 3) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_funny,
            texts.question1Texts[1],
            texts.statusTexts[4],
            texts.button1Texts[1],
            texts.button2Texts[1],
            texts.button3Texts[1]
        );
      } else if (currentQuestion == 3 && choise == 1) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[2],
            texts.statusTexts[2],
            texts.button1Texts[2],
            texts.button2Texts[2],
            texts.button3Texts[2]
        );
      } else if (currentQuestion == 3 && choise == 2) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_likes_player,
            texts.question1Texts[2],
            texts.statusTexts[3],
            texts.button1Texts[2],
            texts.button2Texts[2],
            texts.button3Texts[2]
        );
      } else if (currentQuestion == 3 && choise == 3) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[2],
            texts.statusTexts[2],
            texts.button1Texts[2],
            texts.button2Texts[2],
            texts.button3Texts[2]
        );
      } else if (currentQuestion == 4 && choise == 1) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[3],
            texts.statusTexts[2],
            texts.button1Texts[3],
            texts.button2Texts[3],
            texts.button3Texts[3]
        );
      } else if (currentQuestion == 4 && choise == 2) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_normal,
            texts.question1Texts[3],
            texts.statusTexts[0],
            texts.button1Texts[3],
            texts.button2Texts[3],
            texts.button3Texts[3]
        );
      } else if (currentQuestion == 4 && choise == 3) {
        this.reputation += 2;
        changeElements(
            R.drawable.homelander_likes_player,
            texts.question1Texts[3],
            texts.statusTexts[3],
            texts.button1Texts[3],
            texts.button2Texts[3],
            texts.button3Texts[3]
        );
      } else if (currentQuestion == 5 && choise == 1) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_funny,
            texts.question1Texts[4],
            texts.statusTexts[4],
            texts.button1Texts[4],
            texts.button2Texts[4],
            texts.button3Texts[4]
        );
      } else if (currentQuestion == 5 && choise == 2) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[4],
            texts.statusTexts[2],
            texts.button1Texts[4],
            texts.button2Texts[4],
            texts.button3Texts[4]
        );
      } else if (currentQuestion == 5 && choise == 3) {
        this.reputation += 2;
        changeElements(
            R.drawable.homelander_likes_player,
            texts.question1Texts[4],
            texts.statusTexts[3],
            texts.button1Texts[4],
            texts.button2Texts[4],
            texts.button3Texts[4]
        );
      } else if (currentQuestion == 6 && choise == 1) {
        this.reputation -= 2;
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[5],
            texts.statusTexts[6],
            texts.button1Texts[5],
            texts.button2Texts[5],
            texts.button3Texts[5]
        );
      } else if (currentQuestion == 6 && choise == 2) {
        this.reputation -= 2;
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[5],
            texts.statusTexts[6],
            texts.button1Texts[5],
            texts.button2Texts[5],
            texts.button3Texts[5]
        );
      } else if (currentQuestion == 6 && choise == 3) {
        this.reputation -= 2;
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[5],
            texts.statusTexts[6],
            texts.button1Texts[5],
            texts.button2Texts[5],
            texts.button3Texts[5]
        );
      } else if (currentQuestion == 7 && choise == 1) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_funny,
            texts.question1Texts[6],
            texts.statusTexts[4],
            texts.button1Texts[6],
            texts.button2Texts[6],
            texts.button3Texts[6]
        );
      } else if (currentQuestion == 7 && choise == 2) {
        this.reputation -= 2;
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[6],
            texts.statusTexts[6],
            texts.button1Texts[6],
            texts.button2Texts[6],
            texts.button3Texts[6]
        );
      } else if (currentQuestion == 7 && choise == 3) {
        this.reputation += 2;
        changeElements(
            R.drawable.homelander_likes_player,
            texts.question1Texts[6],
            texts.statusTexts[3],
            texts.button1Texts[6],
            texts.button2Texts[6],
            texts.button3Texts[6]
        );
      } else if (currentQuestion == 8 && choise == 1) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_angry,
            texts.question1Texts[7],
            texts.statusTexts[5],
            texts.button1Texts[7],
            texts.button2Texts[7],
            texts.button3Texts[7]
        );
      } else if (currentQuestion == 8 && choise == 2) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_angry,
            texts.question1Texts[7],
            texts.statusTexts[5],
            texts.button1Texts[7],
            texts.button2Texts[7],
            texts.button3Texts[7]
        );
      } else if (currentQuestion == 8 && choise == 3) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_likes_player,
            texts.question1Texts[7],
            texts.statusTexts[3],
            texts.button1Texts[7],
            texts.button2Texts[7],
            texts.button3Texts[7]
        );
      } else if (currentQuestion == 9 && choise == 1) {
        this.reputation -= 2;
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[8],
            texts.statusTexts[6],
            texts.button1Texts[8],
            texts.button2Texts[8],
            texts.button3Texts[8]
        );
      } else if (currentQuestion == 9 && choise == 2) {
        this.reputation += 1;
        changeElements(
            R.drawable.homelander_normal,
            texts.question1Texts[8],
            texts.statusTexts[0],
            texts.button1Texts[8],
            texts.button2Texts[8],
            texts.button3Texts[8]
        );
      } else if (currentQuestion == 9 && choise == 3) {
        this.reputation -= 1;
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[8],
            texts.statusTexts[2],
            texts.button1Texts[8],
            texts.button2Texts[8],
            texts.button3Texts[8]
        );
      } else if (currentQuestion == 10 && choise == 1) {
        this.reputation -= 1;
        displayButtonLayout();
        changeElements(
            R.drawable.homelander_indifferent,
            texts.question1Texts[8],
            texts.statusTexts[2],
            texts.button1Texts[8],
            texts.button2Texts[8],
            texts.button3Texts[8]
        );
      } else if (currentQuestion == 10 && choise == 2) {
        this.reputation += 1;
        displayButtonLayout();
        changeElements(
            R.drawable.homelander_likes_player,
            texts.question1Texts[8],
            texts.statusTexts[3],
            texts.button1Texts[8],
            texts.button2Texts[8],
            texts.button3Texts[8]
        );
      } else if (currentQuestion == 10 && choise == 3) {
        this.reputation -= 2;
        displayButtonLayout();
        changeElements(
            R.drawable.homelander_kill_thougths,
            texts.question1Texts[8],
            texts.statusTexts[6],
            texts.button1Texts[8],
            texts.button2Texts[8],
            texts.button3Texts[8]
        );
      }

    System.out.println("Reputação: " + this.reputation);
    System.out.println("Pergunta atual: " + this.currentQuestion);

    if (this.reputation <= -5){
      displayResultLayout();
    }
  }

  public void hideElements(){
    this.question.setVisibility(View.GONE);
    this.option1.setVisibility(View.GONE);
    this.option2.setVisibility(View.GONE);
    this.option3.setVisibility(View.GONE);
  }

  public void displayButtonLayout(){
    hideElements();
    this.resultButtonLayout.setVisibility(View.VISIBLE);
    this.resultButton.setVisibility(View.VISIBLE);
  }

  public void displayResultLayout(){
    hideElements();
    this.status.setVisibility(View.GONE);
    this.resultTextLayout.setVisibility(View.VISIBLE);
    this.resultText.setVisibility(View.VISIBLE);
    this.restartButton.setVisibility(View.VISIBLE);

    if(this.reputation >= 5){
      this.homelanderMainImage.setImageResource(R.drawable.homelander_good_end);
      this.resultText.setText(texts.resultTexts[0]);
    }
    else if(this.reputation < 5 && this.reputation > -5){
      this.homelanderMainImage.setImageResource(R.drawable.homelander_normal);
      this.resultText.setText(texts.resultTexts[1]);
    }
    else if (this.reputation <= -5){

      int randomTextPosition = randomNumber(2, 6);
      System.out.println("Posiçao aleatória do texto: " + randomTextPosition);

      this.homelanderMainImage.setImageResource(R.drawable.homelander_bad_end);
      this.resultText.setText(texts.resultTexts[randomTextPosition]);
    }
  }

  public static int randomNumber(int min, int max){
    return min + new Random().nextInt(max - min +1);
  }

  public void clickOption1(){
    showResult(1);
  }

  public void clickOption2(){
    showResult(2);
  }

  public void clickOption3(){
    showResult(3);
  }


}
