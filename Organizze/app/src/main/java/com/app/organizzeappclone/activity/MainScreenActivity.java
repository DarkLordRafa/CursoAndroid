package com.app.organizzeappclone.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.app.organizzeappclone.adapter.AdapterMovimentacao;
import com.app.organizzeappclone.config.FirebaseConfig;
import com.app.organizzeappclone.helper.Base64Custom;
import com.app.organizzeappclone.model.Transaction;
import com.app.organizzeappclone.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.organizzeappclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

  private MaterialCalendarView calendarView;
  private TextView textViewMessage;
  private TextView textViewBalance;
  private DatabaseReference firebaseRef = FirebaseConfig.getFirebaseDatabase();
  private FirebaseAuth auth = FirebaseConfig.getAuth();
  private DatabaseReference userRef;
  private ValueEventListener userValueEventListener;
  private RecyclerView recyclerTransactions;
  private AdapterMovimentacao transactionsAdapter;
  private List<Transaction> transactionList = new ArrayList<>();
  private Transaction transaction;
  private DatabaseReference transactionRef;
  private String monthYear;
  private ValueEventListener valueEventListenerTransactions;
  private LinearLayout balanceLayout;
  private Toolbar toolbar;

  private Double totalCost = 0.0;
  private Double totalGain = 0.0;
  private Double userBalance = 0.0;
  private boolean doubleBackToExitPressedOnce = false;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_screen);
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setTitle("Painel");

    /*
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });
    */

    balanceLayout = findViewById(R.id.balanceLayout);

    transactionsAdapter = new AdapterMovimentacao(transactionList, this);

    calendarView =  findViewById(R.id.calendarView);
    textViewMessage = findViewById(R.id.textViewMessage);
    textViewBalance = findViewById(R.id.textViewBalance);
    recyclerTransactions = findViewById(R.id.recyclerTransactions);

    setCalendarViewConfig();
    swipe();

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerTransactions.setLayoutManager(layoutManager);
    recyclerTransactions.setHasFixedSize(true);
    recyclerTransactions.setAdapter(transactionsAdapter);
  }

  public void swipe(){
    ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
      @Override
      public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
      }

      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        deleteTransaction(viewHolder);
      }
    };
    new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerTransactions);
  }

  public void deleteTransaction(final RecyclerView.ViewHolder viewHolder){
    AlertDialog.Builder alertDialogDelete = new AlertDialog.Builder(this);
    alertDialogDelete.setTitle("Excluir item");
    alertDialogDelete.setMessage("Excluir o item selecionado?");
    alertDialogDelete.setCancelable(false);
    alertDialogDelete.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        int position = viewHolder.getAdapterPosition();
        transaction = transactionList.get(position);

        String userEmail = auth.getCurrentUser().getEmail();
        String userId = Base64Custom.code(userEmail);
        transactionRef = firebaseRef.child("transaction")
            .child(userId)
            .child(monthYear);

        transactionRef.child(transaction.getId()).removeValue();
        transactionsAdapter.notifyItemRemoved(position);
        updateBalance();
      }
    });

    alertDialogDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(
            getApplicationContext(),
            "Cancelado",
            Toast.LENGTH_SHORT
        ).show();
        transactionsAdapter.notifyDataSetChanged();
      }
    });

    AlertDialog alert = alertDialogDelete.create();
    alert.show();
  }

  public void updateBalance(){
    String userEmail = auth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    userRef = firebaseRef.child("users").child(userId);

    if (transaction.getType().equals("gain")){
      totalGain = totalGain - transaction.getValue();
      userRef.child("totalGain").setValue(totalGain);
    }
    else if (transaction.getType().equals("cost")){
      totalCost = totalCost - transaction.getValue();
      userRef.child("totalCost").setValue(totalCost);
    }
  }

  public void recoverTransactions(){
    String userEmail = auth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    transactionRef = firebaseRef.child("transaction")
    .child(userId)
    .child(monthYear);

    valueEventListenerTransactions = transactionRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        transactionList.clear();

        for (DataSnapshot data: dataSnapshot.getChildren()){
          Transaction transaction = data.getValue(Transaction.class);
          transaction.setId(data.getKey());
          transactionList.add(transaction);
        }

        transactionsAdapter.notifyDataSetChanged();
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  public void recoverResume(){
    String userEmail = auth.getCurrentUser().getEmail();
    String userId = Base64Custom.code(userEmail);
    userRef = firebaseRef.child("users").child(userId);

    userValueEventListener = userRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        User user = dataSnapshot.getValue(User.class);
        totalCost = user.getTotalCost();
        totalGain = user.getTotalGain();
        userBalance = totalGain - totalCost;
        if (userBalance < 0){
          balanceLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryCost));
          toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryCost));
          Window window = getWindow();
          window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
          window.setStatusBarColor(ContextCompat.getColor(
              getApplicationContext(),
              R.color.colorPrimaryCost)
          );
        }
        else {
          balanceLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
          toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
          Window window = getWindow();
          window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
          window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
          window.setStatusBarColor(ContextCompat.getColor(
              getApplicationContext()
              ,R.color.colorPrimary)
          );
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        String formatedBalance = decimalFormat.format(userBalance);


        textViewMessage.setText(user.getName());
        textViewBalance.setText("R$ " + formatedBalance);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()){
      case R.id.logOutOption:
        auth.signOut();
        startActivity(new Intent(
            this,
            MainActivity.class
        ));
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  public void addCost(View v){
    startActivity(new Intent(
        this,
        CostActivity.class
    ));
  }

  public void addGain(View v){
    startActivity(new Intent(
        this,
        GainActivity.class
    ));
  }

  public void logOut(View v){
    auth.signOut();
    startActivity(new Intent(
        this,
        MainActivity.class
    ));
  }

  public void setCalendarViewConfig(){
    CalendarDay currentDate = calendarView.getCurrentDate();
    String month = String.format("%02d", (currentDate.getMonth() + 1));
    monthYear = String.valueOf(month + "" + currentDate.getYear());

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
      @Override
      public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

        String month = String.format("%02d", (date.getMonth() + 1));
        monthYear = String.valueOf(month + "" + date.getYear());
        transactionRef.removeEventListener(valueEventListenerTransactions);
        recoverTransactions();
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    recoverResume();
    recoverTransactions();
  }

  @Override
  protected void onStop() {
    super.onStop();
    userRef.removeEventListener(userValueEventListener);
    transactionRef.removeEventListener(valueEventListenerTransactions);
  }

  @Override
  public void onBackPressed() {
    if (doubleBackToExitPressedOnce) {
      super.onBackPressed();
      finishAffinity();
      return;
    }

    this.doubleBackToExitPressedOnce = true;
    Toast.makeText(
        this,
        "Pressione novamente para sair",
        Toast.LENGTH_SHORT).show();

    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

      @Override
      public void run() {
        doubleBackToExitPressedOnce=false;
      }
    }, 2000);
  }

}
