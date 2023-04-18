package com.app.mynotes;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

  private Context context;
  private SharedPreferences preferences;
  private final String PREFERENCES_FILE = "note.preferences";
  private SharedPreferences.Editor editor;
  private final String NOTE_KEY = "note_key";


  public UserPreferences(Context c) {
    this.context = c;
    this.preferences = context.getSharedPreferences(PREFERENCES_FILE, 0);
    editor = preferences.edit();
  }

  public void saveNotes(String note){
    editor.putString(NOTE_KEY, note);
    editor.commit();
  }

  public String getNotes(){
    return preferences.getString(NOTE_KEY, "");
  }

}
