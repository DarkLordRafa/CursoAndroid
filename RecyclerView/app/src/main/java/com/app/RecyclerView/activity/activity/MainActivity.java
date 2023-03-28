package com.app.RecyclerView.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.RecyclerView.R;
import com.app.RecyclerView.activity.ClickListener;
import com.app.RecyclerView.activity.adapter.Adapter;
import com.app.RecyclerView.activity.model.Filmes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  //1- Criamos a variável do RecyclerView
  RecyclerView recyclerView;

  /*
    14- Criando uma lista do tipo da nossa classe Filmes que vai recber
    um novo ArrayList
  */
  private List<Filmes> movieList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //2- Definimos o RecyclerView à variável
    recyclerView = findViewById(R.id.recyclerView);

    //4- Criamos um adapter (ver Adapter.java)
    //22- Passamos nossa lista como argumento
    //23- Ver Adapter.java
    Adapter adapter = new Adapter( movieList);

    //3- Definimos o LayoutManager e o tamanho fixo. (item decoration é opicional)
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

    //11- Setamos nosso adapter criado
    //12- Criar um objeto que será a nossa lista (ver Filmes.java)
    recyclerView.setAdapter(adapter);

    this.createMovies();

    recyclerView.addOnItemTouchListener(new ClickListener(
        getApplicationContext(),
        recyclerView,
        new ClickListener.OnItemClickListener() {
          @Override
          public void onItemClick(View view, int position) {

            Filmes movie = movieList.get(position);

            Toast.makeText(
                getApplicationContext(),
                "Título: " + movie.getTitle(),
                Toast.LENGTH_SHORT
            ).show();
          }

          @Override
          public void onLongItemClick(View view, int position) {

            Filmes movie = movieList.get(position);

            Toast.makeText(
                getApplicationContext(),
                "Título: " + movie.getTitle() + "\n" + "Gênero: " + movie.getGender()
                + "\n" + "Ano: " + movie.getYear(),
                Toast.LENGTH_LONG
            ).show();
          }

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          }
        }
    ));
  }

  //15- Criando um método para configurar nossa lsta
  public void createMovies(){

    /*
      16- Criando uma variável que vai receber nossa classe (objeto) Filmes
      já passsando as informações nos argumentos
    */
    Filmes filme = new Filmes("Filme teste", "2015", "Ação");
    //17- Adicionando essa variável à nossa lista criada lá em cima
    this.movieList.add(filme);

    //18- Repetindo o processo com a variável já criada
    //19- Ver Adapter.java

    filme = new Filmes("Cavaleiro", "1990", "Ação");
    this.movieList.add(filme);

    filme = new Filmes("Fantasma", "2002", "Romance");
    this.movieList.add(filme);

    filme = new Filmes("Blade", "2005", "Ação");
    this.movieList.add(filme);

    filme = new Filmes("Matrix", "2005", "Ficção");
    this.movieList.add(filme);

    filme = new Filmes("A Casa", "2010", "Terror");
    this.movieList.add(filme);

    filme = new Filmes("Outros", "2018", "Terror");
    this.movieList.add(filme);

    filme = new Filmes("Marge", "2004", "Comédia");
    this.movieList.add(filme);

    filme = new Filmes("Invasão", "2015", "Ficção");
    this.movieList.add(filme);

    filme = new Filmes("Dredd", "2005", "Ficção");
    this.movieList.add(filme);

    filme = new Filmes("A Lua", "1999", "Comédia");
    this.movieList.add(filme);

    filme = new Filmes("Segunda Guerra", "2015", "Ação");
    this.movieList.add(filme);

    filme = new Filmes("Marge 2", "2008", "Comédia");
    this.movieList.add(filme);

    filme = new Filmes("Invasão 2", "2020", "Ficção");
    this.movieList.add(filme);

    filme = new Filmes("Fight", "2005", "Ação");
    this.movieList.add(filme);

    filme = new Filmes("Carros", "2005", "Aventura");
    this.movieList.add(filme);

    filme = new Filmes("Outros 2", "2022", "Terror");
    this.movieList.add(filme);

    filme = new Filmes("Infecção", "2004", "Terror");
    this.movieList.add(filme);

    filme = new Filmes("Eles", "2010", "Aventura");
    this.movieList.add(filme);

  }

}
