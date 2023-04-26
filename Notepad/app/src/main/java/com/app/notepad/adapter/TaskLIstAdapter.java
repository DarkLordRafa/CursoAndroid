package com.app.notepad.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.notepad.R;
import com.app.notepad.model.TaskList;

import java.util.List;

public class TaskLIstAdapter extends RecyclerView.Adapter<TaskLIstAdapter.MyViewHolder> {

  private List<TaskList> taskList;

  public TaskLIstAdapter(List<TaskList> list) {
    this.taskList = list;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View taskListView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.task_list_adapter, parent, false);

    return new MyViewHolder(taskListView);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    TaskList task = taskList.get(position);
    holder.taskText.setText(task.getTaskName());
  }

  @Override
  public int getItemCount() {
    return this.taskList.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder{

    TextView taskText;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);

      taskText = itemView.findViewById(R.id.taskText);
    }

  }

}
