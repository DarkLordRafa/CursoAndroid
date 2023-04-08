package com.app.caixaapp.user_classes;

import android.widget.EditText;
import android.widget.TextView;


public class UserAccount{

  public TextView userBalance;

  public EditText depositInput;

  public EditText withdrawalInput;

  /*
    Esse valor poderia vir de um banco de dados,
    por isso o uso do private é importante para
    as outras classes não terem acesso
  */
  private double userBalanceValue = 1000;

  public double getUserBalanceValue() {
    return userBalanceValue;
  }


  /*
  Só estes métodos é que poderiam alterar o valor do saldo,
  eles teriam validações e depois o saldo atualizado pode ser
  exibido na tela com o método getUserBalanceValue
  */
  public void deposit(){
    EditText depositInput = this.depositInput;
    String depositInputString = depositInput.getText().toString();
    Double depositValue = Double.parseDouble(depositInputString);
    this.userBalanceValue += depositValue;
    this.userBalance.setText(String.format("%.2f", userBalanceValue));
    this.depositInput.setText("");
  }

  public void withdrawal(){
    EditText withdrawalInput = this.withdrawalInput;
    String withdrawalInputString = withdrawalInput.getText().toString();
    Integer withdrawalValue = Integer.parseInt(withdrawalInputString);
    this.userBalanceValue -= withdrawalValue;
    this.userBalance.setText(String.format("%.2f", userBalanceValue));
    this.withdrawalInput.setText("");
  }

}
