package com.app.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  boolean canPlay = true;
  Score score = new Score();



  public void rockChoise(View v){
    playerChoice(0);
  }

  public void paperChoise(View v){
    playerChoice(1);
  }

  public void scisorChoise(View v){
    playerChoice(2);
  }

  public void playerChoice(int playerChosenNumber){
    if (canPlay) {
      TextView playerScore = findViewById(R.id.playerScore);
      TextView computerScore = findViewById(R.id.computerScore);
      TextView playerReputation = findViewById(R.id.reputationValue);
      ImageView resultImage = findViewById(R.id.resultImage);
      TextView resultText = findViewById(R.id.resultText);
      score.trophyImage = findViewById(R.id.trophyImage);
      int computerChoiseNumber = new Random().nextInt(3);
      canPlay = false;


      switch (computerChoiseNumber) {
        case 0:
          resultImage.setImageResource(R.drawable.pedra);
          break;

        case 1:
          resultImage.setImageResource(R.drawable.papel);
          break;

        case 2:
          resultImage.setImageResource(R.drawable.tesoura);
          break;
      }

      if (
          playerChosenNumber == 0 && computerChoiseNumber == 2
              || playerChosenNumber == 1 && computerChoiseNumber == 0
              || playerChosenNumber == 2 && computerChoiseNumber == 1
      ) {
        resultText.setText(R.string.playerWinText);
        score.increaseScore(1);
        playerScore.setText(String.valueOf(score.getplayerScoreValue()));
      } else if (
          playerChosenNumber == 0 && computerChoiseNumber == 1
              || playerChosenNumber == 1 && computerChoiseNumber == 2
              || playerChosenNumber == 2 && computerChoiseNumber == 0
      ) {
        resultText.setText(R.string.computerWinText);
        score.increaseScore(2);
        computerScore.setText(String.valueOf(score.getcomputerScoreValue()));
      } else {
        resultText.setText(R.string.drawText);
      }

      playerReputation.setText(score.getPlayerReputation());


      Handler handler = new Handler();
      handler.postDelayed(new Runnable() {
        ImageView resultImage = findViewById(R.id.resultImage);
        TextView resultText = findViewById(R.id.resultText);

        @Override
        public void run() {
          resultText.setText(R.string.defaultResultText);
          resultImage.setImageResource(R.drawable.padrao);
          canPlay = true;
        }
      }, 2500);
    }

  }


}
