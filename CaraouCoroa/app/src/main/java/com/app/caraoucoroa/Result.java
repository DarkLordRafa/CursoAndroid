package com.app.caraoucoroa;


import java.io.Serializable;

public class Result implements Serializable {

  private String resultTextString;
  private int coinImage;

  public String getResultTextString() {
    return resultTextString;
  }

  public void setResultTextString(String resultTextString) {
    this.resultTextString = resultTextString;
  }

  public int getCoinImage() {
    return coinImage;
  }

  public void setCoinImage(int coinImage) {
    this.coinImage = coinImage;
  }
}
