package com.app.notepad.helper;

import com.app.notepad.model.TaskList;

import java.util.List;

public interface TaskDAOInterface {

  public boolean saveTask(TaskList task);
  public boolean updateTask(TaskList task);
  public boolean deleteTask(TaskList task);
  public List<TaskList> listTasks();
}
