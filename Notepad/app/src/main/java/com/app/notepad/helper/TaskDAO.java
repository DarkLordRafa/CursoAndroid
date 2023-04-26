package com.app.notepad.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.notepad.model.TaskList;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements TaskDAOInterface{

  private SQLiteDatabase write;
  private SQLiteDatabase read;

  public TaskDAO(Context context) {
    DbHelper dbHelper = new DbHelper(context);
    write = dbHelper.getWritableDatabase();
    read = dbHelper.getReadableDatabase();
  }

  @Override
  public boolean saveTask(TaskList task) {

    ContentValues cv = new ContentValues();
    cv.put("nome", task.getTaskName());

    try{
      write.insert(DbHelper.TABLE_TASKS, null, cv);
      Log.i("DB_TASK_INFO SAVE", "Tarefa salva");
    }
    catch (Exception e){
      Log.i("DB_TASK_INFO SAVE", "Erro ao salvar a tarefa: " + e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public boolean updateTask(TaskList task) {
    ContentValues cv = new ContentValues();
    cv.put("nome", task.getTaskName());

    try{
      String[] args = {task.getId().toString()};
      write.update(DbHelper.TABLE_TASKS, cv, "id=?", args);
      Log.i("DB_TASK_INFO SAVE", "Tarefa atualizada");
    }
    catch (Exception e){
      Log.i("DB_TASK_INFO SAVE", "Erro ao atualizar a tarefa: " + e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public boolean deleteTask(TaskList task) {
    ContentValues cv = new ContentValues();
    cv.put("nome", task.getTaskName());

    try{
      String[] args = {task.getId().toString()};
      write.delete(DbHelper.TABLE_TASKS, "id=?", args);
      Log.i("DB_TASK_INFO SAVE", "Tarefa deletada");
    }
    catch (Exception e){
      Log.i("DB_TASK_INFO SAVE", "Erro ao deletar a tarefa: " + e.getMessage());
      return false;
    }

    return true;
  }

  @Override
  public List<TaskList> listTasks() {
    List<TaskList> taskList = new ArrayList<>();
    String sql = "SELECT * FROM " + DbHelper.TABLE_TASKS + ";";
    Cursor cursor = write.rawQuery(sql, null);

    while(cursor.moveToNext()){
      TaskList task = new TaskList();
      Long taskId = cursor.getLong(cursor.getColumnIndex("id"));
      String taskName = cursor.getString(cursor.getColumnIndex("nome"));

      task.setId(taskId);
      task.setTaskName(taskName);
      taskList.add(task);
    }

    return taskList;
  }
}
