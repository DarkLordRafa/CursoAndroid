package com.app.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class ResultActivity extends AppCompatActivity {

  ImageView coinImageView;
  Button returnButton;
  TextView resultText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_result);

    returnButton = findViewById(R.id.returnButton);
    coinImageView = findViewById(R.id.coinImageView);
    resultText = findViewById(R.id.resultText);

    returnButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        finish();

      }
    });

    /*
      getExtras retorna uma lista com todos os Extras,
      e acessamos eles depois pelo nome da chave deles
    */

    Bundle data = getIntent().getExtras();

    /*
    Criamos uma propriedade do tipo Result que vai receber nosso serializable,
    mas antes precisamos converter esse serializable para o tipo Result.
    Fazemos isso colocando (Result) na frente
    */
    /*
    Resumindo é apenas uma propriedade do tipo Result que recebe um
    objeto Result já preenchido
    */
    Result result = (Result) data.getSerializable("result");

    resultText.setText(result.getResultTextString());
    coinImageView.setImageResource(result.getCoinImage());
  }

}
