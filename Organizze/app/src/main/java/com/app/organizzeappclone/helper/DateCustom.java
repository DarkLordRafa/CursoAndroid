package com.app.organizzeappclone.helper;

import java.text.SimpleDateFormat;

public class DateCustom {

  public static String currentDate(){
    long date = System.currentTimeMillis();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String dateString = simpleDateFormat.format(date);
    return dateString;
  }

  public static String date(String inputDate){
    String outputDate[] = inputDate.split("/");
    String day = outputDate[0];
    String month = outputDate[1];
    String year = outputDate[2];
    String monthYear = month + year;

    return monthYear;
  }

}
