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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


public class CostActivity extends AppCompatActivity {

  private TextInputEditText editTextCostDate,
      editTextCostCategory,
      editTextCostDescription;

  private EditText editTextCostValue;
  private Transaction transaction;
  private DatabaseReference firebaseRef = FirebaseConfig.getFirebaseDatabase();
  private FirebaseAuth firebaseAuth = FirebaseConfig.getAuth();
  private Double totalCost;
  private FloatingActionButton fabCostConfirm;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_cost);

    editTextCostValue = findViewById(R.id.editTextCostValue);
    editTextCostDate = findViewById(R.id.editTextCostDate);
    editTextCostCategory = findViewById(R.id.editTextCostCategory);
    editTextCostDescription = findViewById(R.id.editTextCostDescription);
    fabCostConfirm = findViewById(R.id.fabCostConfirm);

    recoverTotalCost();

    fabCostConfirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        saveCost();
      }
    });
  }

  public void saveCost(){

    if (validateCost()){
      transaction = new Transaction();
      String date = editTextCostDate.getText().toString();
      Double valueText = Double.parseDouble(editTextCostValue.getText().toString());

      transaction.setValue(valueText);
      transaction.setCategory(editTextCostCategory.getText().toString());
      transaction.setDescription(editTextCostDescription.getText().toString());
      transaction.setDate(date);
      transaction.setType("cost");

      Double updatedCost = totalCost + valueText;
      updateTotalCost(updatedCost);

      transaction.saveTransaction(date);
      finish();
    }
  }

  public boolean validateCost(){

    String valueText = editTextCostValue.getText().toString();
    String dateText = editTextCostDate.getText().toString();
    String descriptionText = editTextCostDescription.getText().toString();
    String categoryText = editTextCostCategory.getText().toString();

    if (!valueText.isEmpty()){
      if (!dateText.isEmpty()){
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

  public void recoverTotalCost(){
    String userEmail = firebaseAuth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    DatabaseReference userRef = firebaseRef.child("users").child(userId);

    userRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        User user = dataSnapshot.getValue(User.class);
        totalCost = user.getTotalCost();
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  public void updateTotalCost(Double updatedCost){
    String userEmail = firebaseAuth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    DatabaseReference userRef = firebaseRef.child("users").child(userId);

    userRef.child("totalCost").setValue(updatedCost);
  }

}
