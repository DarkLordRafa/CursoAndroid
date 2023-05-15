package com.app.organizzeappclone.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.organizzeappclone.R;
import com.app.organizzeappclone.config.FirebaseConfig;
import com.app.organizzeappclone.helper.Base64Custom;
import com.app.organizzeappclone.model.Transaction;
import com.app.organizzeappclone.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;



public class GainActivity extends AppCompatActivity {

  private TextInputEditText editTextGainDate,
      editTextGainCategory,
      editTextGainDescription;

  private EditText editTextGainValue;
  private Transaction transaction;
  private DatabaseReference firebaseRef = FirebaseConfig.getFirebaseDatabase();
  private FirebaseAuth firebaseAuth = FirebaseConfig.getAuth();
  private Double totalGain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gain);

    editTextGainValue = findViewById(R.id.editTextGainValue);
    editTextGainDate = findViewById(R.id.editTextGainDate);
    editTextGainCategory = findViewById(R.id.editTextGainCategory);
    editTextGainDescription = findViewById(R.id.editTextGainDescription);

    recoverTotalGain();
  }

  public void recoverTotalGain(){
    String userEmail = firebaseAuth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    DatabaseReference userRef = firebaseRef.child("users").child(userId);

    userRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        User user = dataSnapshot.getValue(User.class);
        totalGain = user.getTotalGain();
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }


  public boolean validateGain(){

    String valueText = editTextGainValue.getText().toString();
    String dateText = editTextGainDate.getText().toString();
    String descriptionText = editTextGainDescription.getText().toString();
    String categoryText = editTextGainCategory.getText().toString();

    if (!valueText.isEmpty()){
      if (!dateText.isEmpty()){
        if (!dateText.matches("\\d{2}/\\d{2}/\\d{4}")) {
          Toast.makeText(
              getApplicationContext(),
              "A data deve estar no formato:\n \"dd/mm/aaaa\"",
              Toast.LENGTH_LONG
          ).show();
          return false;
        }

        if (!descriptionText.isEmpty()){
          if (!categoryText.isEmpty()){
            return true;
          }
          else {
            Toast.makeText(
                getApplicationContext(),
                "Categoria não preenchida",
                Toast.LENGTH_SHORT
            ).show();
            return false;
          }
        }
        else {
          Toast.makeText(
              getApplicationContext(),
              "Descrição não preenchida",
              Toast.LENGTH_SHORT
          ).show();
          return false;
        }

      }
      else {
        Toast.makeText(
            getApplicationContext(),
            "Data não preenchida",
            Toast.LENGTH_SHORT
        ).show();
        return false;
      }
    }
    else {
      Toast.makeText(
          getApplicationContext(),
          "Valor não preenchido",
          Toast.LENGTH_SHORT
      ).show();
      return false;
    }
  }


  public void updateTotalGain(Double updatedGain){
    String userEmail = firebaseAuth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    DatabaseReference userRef = firebaseRef.child("users").child(userId);

    userRef.child("totalGain").setValue(updatedGain);
  }


  public void saveGain(View v){

    if (validateGain()){
      transaction = new Transaction();
      String date = editTextGainDate.getText().toString();
      Double valueText = Double.parseDouble(editTextGainValue.getText().toString());

      transaction.setValue(valueText);
      transaction.setCategory(editTextGainCategory.getText().toString());
      transaction.setDescription(editTextGainDescription.getText().toString());
      transaction.setDate(date);
      transaction.setType("gain");

      Double updatedGain = totalGain + valueText;
      updateTotalGain(updatedGain);

      transaction.saveTransaction(date);
      finish();
    }
  }

}
