package com.app.tips;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  private EditText payInput;
  private TextView tipPercentageText;
  private SeekBar seekBarTip;
  private TextView tipText;
  private TextView totalText;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    payInput = findViewById(R.id.payInput);
    tipPercentageText = findViewById(R.id.tipPercentageText);
    seekBarTip = findViewById(R.id.seekBarTip);
    tipText = findViewById(R.id.tipText);
    totalText = findViewById(R.id.totalText);

    setSeekBarTipListener();
    setPayInputListener();
  }


  public void calculateTip(int progressValue){
    String payInputString = payInput.getText().toString();
    boolean validInput;

    tipPercentageText.setText(progressValue + "%");

    if(payInputString == null){
      validInput = false;
    }
    else{
      validInput = true;
    }

    if(validInput){
      if (payInputString.equals("")){
        payInputString = "0";
      }

      double payInputValue = Double.parseDouble(payInputString);
      String progressString = String.valueOf(progressValue);
      double progressDouble = Double.parseDouble(progressString);
      double tip = payInputValue * (progressDouble / 100);
      double total = payInputValue + tip;
      String tipString = String.valueOf(tip);
      String totalString = String.valueOf(total);
      tipText.setText("R$: " + tipString);
      totalText.setText("R$: " + totalString);
    }
  }


  public void setSeekBarTipListener() {
    seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        calculateTip(progress);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }


  public void setPayInputListener(){
    payInput.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        calculateTip(seekBarTip.getProgress());
      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });
  }
}
