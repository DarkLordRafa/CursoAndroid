package com.app.cardview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.cardview.R;
import com.app.cardview.model.Posts;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

  private List<Posts> posts;

  public CardAdapter(List<Posts> userPosts) {
    this.posts = userPosts;
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View itemList = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.card_adapter_layout,
        parent,
        false
    );

    return new MyViewHolder(itemList);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    Posts post = posts.get(position);

    holder.userNamne.setText(post.getUserName());
    holder.postDescription.setText(post.getPostDescription());
    holder.postImage.setImageResource(post.getPostImage());
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView userNamne;
    private TextView postDescription;
    private ImageView postImage;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      userNamne = itemView.findViewById(R.id.userNamne);
      postDescription = itemView.findViewById(R.id.postDescription);
      postImage = itemView.findViewById(R.id.postImage);
    }
  }

}
