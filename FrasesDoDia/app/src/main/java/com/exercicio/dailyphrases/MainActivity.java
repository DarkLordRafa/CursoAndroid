package com.exercicio.dailyphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

  String[] texts = {
      "Nem todas as tempestades vêm para atrapalhar a sua vida. Algumas vêm para limpar seu caminho.",
      "Não se desespere quando a caminhada estiver difícil, é sinal de que você está no caminho certo.",
      "Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado.",
      "O insucesso é apenas uma oportunidade para recomeçar com mais inteligência.",
      "Lute com determinação, abrace a vida com paixão, perca com classe e vença com ousadia, porque o mundo pertence a quem se atreve e a vida é muito para ser insignificante."
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void randomText(View view){
    TextView mainText = findViewById(R.id.mainText);
    int randomNumber = new Random().nextInt(5);

    mainText.setText(texts[randomNumber]);
  }

}



