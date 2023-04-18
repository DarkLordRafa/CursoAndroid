package com.app.mynotes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  private UserPreferences userPreferences;
  private EditText userNoteText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    userPreferences = new UserPreferences(getApplicationContext());
    userNoteText = findViewById(R.id.editTextNote);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        String userText = userNoteText.getText().toString();

        if (userText.equals("")){
          Snackbar.make(view, "A anotação não pode estar vazia", Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }
        else {
          userPreferences.saveNotes(userText);
          Snackbar.make(view, "Anotação salva", Snackbar.LENGTH_LONG)
              .setAction("Action", null).show();
        }

      }
    });

    String userNote = userPreferences.getNotes();
    if (!userNote.equals("")){
      userNoteText.setText(userNote);
    }
  }

}
