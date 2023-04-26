package com.app.notepad.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.app.notepad.R;
import com.app.notepad.adapter.TaskLIstAdapter;
import com.app.notepad.helper.RecyclerItemClickListener;
import com.app.notepad.helper.TaskDAO;
import com.app.notepad.model.TaskList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerViewTaskList;
  private TaskLIstAdapter taskLIstAdapter;
  private List<TaskList> taskList = new ArrayList<>();
  private TaskList selectedTask;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    recyclerViewTaskList = findViewById(R.id.recyclerViewTaskList);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent addTaskIntent = new Intent(getApplicationContext(), AddTaskActivity.class);
        startActivity(addTaskIntent);
      }
    });

    recyclerViewTaskList.addOnItemTouchListener(
        new RecyclerItemClickListener(
            getApplicationContext(),
            recyclerViewTaskList,
            new RecyclerItemClickListener.OnItemClickListener() {
              @Override
              public void onItemClick(View view, int position) {
                TaskList selectedTask = taskList.get(position);
                Intent intent = new Intent(
                    MainActivity.this,
                    AddTaskActivity.class
                );
                intent.putExtra("selectedTask", selectedTask);
                startActivity(intent);
              }

              @Override
              public void onLongItemClick(View view, int position) {
                selectedTask = taskList.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Deletar tarefa");
                dialog.setMessage("Deseja deletar a tarefa: " + selectedTask.getTaskName() + "?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    TaskDAO taskDAO = new TaskDAO(getApplicationContext());
                    if (taskDAO.deleteTask(selectedTask)){
                      loadTaskList();
                      Toast.makeText(
                          getApplicationContext(),
                          "Tarefa deletada",
                          Toast.LENGTH_SHORT
                      ).show();
                    }
                    else {
                      Toast.makeText(
                          getApplicationContext(),
                          "Erro ao deletar",
                          Toast.LENGTH_SHORT
                      ).show();
                    }
                  }
                });

                dialog.setNegativeButton("Não", null);

                dialog.create();
                dialog.show();
              }

              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

              }
            }
        )
    );
  }

  public void loadTaskList(){
    TaskDAO taskDAO = new TaskDAO(getApplicationContext());
    taskList = taskDAO.listTasks();

    taskLIstAdapter = new TaskLIstAdapter(taskList);

    RecyclerView.LayoutManager taskListLayoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerViewTaskList.setLayoutManager(taskListLayoutManager);
    recyclerViewTaskList.setHasFixedSize(true);
    recyclerViewTaskList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
    recyclerViewTaskList.setAdapter(taskLIstAdapter);
  }

  @Override
  protected void onStart() {
    super.onStart();
    loadTaskList();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
