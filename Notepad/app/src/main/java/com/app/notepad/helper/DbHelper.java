package com.app.notepad.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper {

  public static String DB_NAME = "DB_NOTEPAD";
  public static int VERSION = 1;
  public static String TABLE_TASKS = "tarefas";


  public DbHelper(@Nullable Context context) {

    super(context, DB_NAME, null, VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS
        + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        " nome TEXT NOT NULL); ";

    try {
      db.execSQL(sql);
      Log.i("INFO DB_NOTEPAD", "Tabela criada");
    }
    catch (Exception e){
      e.printStackTrace();
      Log.i("INFO DB_NOTEPAD", "Erro ao criar a tabela: " + e.getMessage());
    }

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    try {
      Log.i(
          "INFO DB_NOTEPAD",
          "App atualizado. Versão antiga: " + oldVersion
          + ". Versão atual: " + newVersion
      );
    }
    catch (Exception e){
      e.printStackTrace();
      Log.i("INFO DB_NOTEPAD", "Erro ao atualizar o app: " + e.getMessage());
    }

  }
}
