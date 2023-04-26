package com.app.notepad.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.notepad.R;
import com.app.notepad.helper.TaskDAO;
import com.app.notepad.model.TaskList;
import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {

  private TextInputEditText taskText;
  private TaskList currentTask;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_task);

    taskText = findViewById(R.id.taskText);

    currentTask = (TaskList) getIntent().getSerializableExtra("selectedTask");

    if (currentTask != null){
      taskText.setText(currentTask.getTaskName());
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.save_task_menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    switch(item.getItemId()){
      case R.id.addTask:
        String taskName = taskText.getText().toString();
        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        if (taskName.isEmpty()){
          Toast.makeText(
              getApplicationContext(),
              "Escreva algo",
              Toast.LENGTH_SHORT
          ).show();
        }
        else{
          if (currentTask != null){
            TaskList task = new TaskList();
            task.setTaskName(taskName);
            task.setId(currentTask.getId());
            if (taskDAO.updateTask(task)){
              finish();
              Toast.makeText(
                  getApplicationContext(),
                  "Tarefa atualizada",
                  Toast.LENGTH_SHORT
              ).show();
            }
            else {
              Toast.makeText(
                  getApplicationContext(),
                  "Erro ao atualizar tarefa",
                  Toast.LENGTH_SHORT
              ).show();
            }
          }

          else{
            TaskList task = new TaskList();
            task.setTaskName(taskName);
            if (taskDAO.saveTask(task)){
              finish();
              Toast.makeText(
                  getApplicationContext(),
                  "Tarefa salva",
                  Toast.LENGTH_SHORT
              ).show();
            }
            else {
              Toast.makeText(
                  getApplicationContext(),
                  "Erro ao salvar",
                  Toast.LENGTH_SHORT
              ).show();
            }
          }
        }
        break;
    }

    return super.onOptionsItemSelected(item);
  }
}
