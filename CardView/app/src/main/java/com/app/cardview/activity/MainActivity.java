package com.app.cardview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.app.cardview.R;
import com.app.cardview.adapter.CardAdapter;
import com.app.cardview.model.Posts;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerViewCardView;
  private List<Posts> postList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerViewCardView = findViewById(R.id.recyclerViewCardView);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerViewCardView.setLayoutManager(layoutManager);

    CardAdapter cardAdapter = new CardAdapter(postList);
    recyclerViewCardView.setAdapter(cardAdapter);

    this.loadPosts();
  }

  public void loadPosts(){
    Posts posts = new Posts("Rafael", "Minha viagem", R.drawable.imagem1);
    this.postList.add(posts);

    posts = new Posts("Guilherme", "Usei uma IA pra fazer essa imagem", R.drawable.imagem2);
    this.postList.add(posts);

    posts = new Posts("Maria", "Estou em Paris!", R.drawable.imagem3);
    this.postList.add(posts);

    posts = new Posts("Júlia", "Fazendo trilha", R.drawable.imagem4);
    this.postList.add(posts);
  }

}
