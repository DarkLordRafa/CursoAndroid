package com.app.caixaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.app.caixaapp.user_classes.UserAccount;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /*
      Declarando um novo objeto dentro do método onCreate para
      poder exibir o saldo logo que o app for iniciado
    */
    UserAccount userAccount = new UserAccount();
    userAccount.userBalance =  findViewById(R.id.balanceValue);

    /*
      Não importa se é um novo objeto derivado do original,
      pois o saldo da conta seria obtido através de um banco de dados
    */
    userAccount.userBalance.setText(String.format("%.2f", userAccount.getUserBalanceValue()));

  }

  /*
    Declarando objeto no escopo global para os valores das
    propriedades continuarem salvos
  */
  UserAccount userAccount = new UserAccount();

  public void deposit(View view){
    userAccount.userBalance =  findViewById(R.id.balanceValue);
    userAccount.depositInput = findViewById(R.id.depositInput);
    userAccount.deposit();
  }

  public void withdrawal(View view){
    userAccount.userBalance =  findViewById(R.id.balanceValue);
    userAccount.withdrawalInput = findViewById(R.id.withdrawalInput);
    userAccount.withdrawal();
  }

}
