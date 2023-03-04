package com.app.jokenpo;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class Score extends AppCompatActivity {

  private int playerScoreValue = 0;
  private int computerScoreValue = 0;
  private String playerReputation = "Neutra";
  public ImageView trophyImage;


  public int getplayerScoreValue() {
    return playerScoreValue;
  }

  public int getcomputerScoreValue() {
    return computerScoreValue;
  }

  public String getPlayerReputation() {
    return this.playerReputation;
  }

  private void updateReputation(){
    if (playerScoreValue >= (computerScoreValue + 3)){
      this.playerReputation = "Profissional";
    }
    else if(playerScoreValue > computerScoreValue){
      this.playerReputation = "Sortudo";
    }
    else if(computerScoreValue >= (playerScoreValue + 3)){
      this.playerReputation = "O Pior de Todos";
    }
    else if(computerScoreValue > playerScoreValue){
      this.playerReputation = "Azarado";
    }
    else{
      this.playerReputation = "Neutra";
    }
    if(playerScoreValue >= 10){
      this.playerReputation = "Mestre do Jokenpô";
      trophyImage.setImageResource(R.drawable.trophy_icon);
    }
  }

  public void increaseScore(int targetScore){
    if (targetScore == 1){
      playerScoreValue ++;
    }
    else{
      computerScoreValue ++;
    }
    updateReputation();
  }

}
