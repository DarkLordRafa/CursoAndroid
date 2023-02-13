package com.exercicio.layout2;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

  MediaPlayer openingSound;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    openingSound = MediaPlayer.create(this, R.raw.opening_sound);
    openingSound.start();
  }


  int textPosition = 0;

  String[] texts = {
      "Resident Evil 4 é um remake do Resident Evil 4 original de 2005.\n\n" +
          "Reimaginado para 2023 para trazer horror de sobrevivência de última geração.\n\n" +
          "Resident Evil 4 preserva a essência do jogo original enquanto apresenta jogabilidade modernizada, " +
          "enredo reimaginado e gráficos vívidamente detalhados para fazer deste o mais recente jogo de horror de sobrevivência, " +
          "onde a vida e a morte, o terror e a catarse se cruzam.",
      "Resident Evil 4 Remake é um jogo produzido por Capcom",
      "O jogo será lançado no dia 24 de março",
      "O retorno de um clássico"
  };


  public void changeTextPrevious(View view){
    TextView mainText = findViewById(R.id.mainText);
    if (textPosition > 0){
      textPosition --;
      if (textPosition > 0){
        mainText.setText(texts[textPosition].toUpperCase());
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextBlack));
        mainText.setTextSize(18);
      }
      else {
        mainText.setText(texts[textPosition]);
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextNormal));
        mainText.setTextSize(15);
      }
    }
  }

  public void changeTextNext(View view){
    TextView mainText = findViewById(R.id.mainText);
    if (textPosition < 2){
      textPosition ++;
      if (textPosition > 0){
        mainText.setText(texts[textPosition].toUpperCase());
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextBlack));
        mainText.setTextSize(18);
      }
      else {
        mainText.setText(texts[textPosition]);
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextNormal));
        mainText.setTextSize(15);
      }
    }
  }
}
