package com.app.notepad.model;

import java.io.Serializable;

public class TaskList implements Serializable {

  private Long id;
  private String taskName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }
}
