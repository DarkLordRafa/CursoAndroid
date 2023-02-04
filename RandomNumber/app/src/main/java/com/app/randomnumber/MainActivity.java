package com.app.randomnumber;

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

    public void playAction(View view) {
      TextView numberValue = findViewById(R.id.numberValue);
      int randomNumber = new Random().nextInt(11);
      numberValue.setText("" + randomNumber);
    }
}
