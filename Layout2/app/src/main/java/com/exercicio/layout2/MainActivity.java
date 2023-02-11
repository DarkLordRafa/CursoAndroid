package com.exercicio.layout2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public abstract class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }


  TextView mainText = findViewById(R.id.mainText);
  int textPosition = 0;

  String[] texts = {
      "Resident Evil 4 é um remake do Resident Evil 4 original de 2005.\\n\\n\n" +
          "Reimaginado para 2023 para trazer horror de sobrevivência de última geração.\\n\\n\n" +
          "Resident Evil 4 preserva a essência do jogo original enquanto apresenta jogabilidade modernizada,\n" +
          "enredo reimaginado e gráficos vívidamente detalhados para fazer deste o mais recente jogo de horror de sobrevivência,\n" +
          "onde a vida e a morte, o terror e a catarse se cruzam.",
      "Resident Evil 4 Remake é um jogo produzido por Capcom",
      "O jogo será lançado no dia 24 de março",
      "O retorno de um clássico"
  };

/*
  public void changeTextPrevious(View view){
    if (textPosition > 0){
      textPosition --;
      if (textPosition > 0){
        mainText.setText(texts[textPosition].toUpperCase());
      }
      else {
        mainText.setText(texts[textPosition].toLowerCase());
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextNormal));
      }
    }
  }

  public void changeTextNext(View view){
    if (textPosition < 2){
      textPosition ++;
      if (textPosition > 0){
        mainText.setText(texts[textPosition].toUpperCase());
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextBlack));
      }
      else {
        mainText.setText(texts[textPosition].toLowerCase());
        mainText.setTextColor(this.getResources().getColor(R.color.mainTextNormal));
      }
    }
  }
  */
}
