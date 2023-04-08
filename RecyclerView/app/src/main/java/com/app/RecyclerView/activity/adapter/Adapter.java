package com.app.RecyclerView.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.RecyclerView.R;
import com.app.RecyclerView.activity.model.Filmes;

import java.util.List;

//4- Criando um adapter
/*
  Criamos uma classe adapter que herda RecyclerView.Adapter e precisa de um view holder
  O view holder é uma classe que serve para guardar os dados de cada view,
  para eles não serem perdidos quando saírem da tela
  precisamos criar esse view holder
*/

//6- Implementamos os métodos
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

  //20- Criando uma Lista do tipo Filmes;
  private List<Filmes> movieList;

  //21- Criando um construtor para nosso adapter poder receber uma lista como argumento
  //22- Ver MainActivity.java
  public Adapter(List<Filmes> movieList) {
    this.movieList = movieList;
  }


  /*
    5-Dentro da classe do nosso adapter, criamos uma claase que
    herdará RecyclerView.ViewHolder e depois criamos um construtor pra ela
  */
  public class MyViewHolder extends RecyclerView.ViewHolder{

    // Criandos as variáveis
    TextView titleText;
    TextView yearText;
    TextView genderText;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);

      /*
        10- Atribuindo os elementos às variáveis
        O itemView é quem dá acesso às variáveis da nossa view criada
      */
      //11- Setar o adapter (ver MainActivity)
      titleText = itemView.findViewById(R.id.titleText);
      yearText = itemView.findViewById(R.id.yearText);
      genderText = itemView.findViewById(R.id.genderText);
    }
  }


  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    /*
      7- O método  onCreateViewHolder, cria as primeiras views que serão exibidas
      Para isso, precimos criar a view que queremos em um novo arquivo XML
      (ver adapter_layout.xml)
    */

    //8- Agora criamos uma variável e definimos a nossa view criada à ela (já convertendo)
    View myListview = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.adapter_layout,
        parent,
        false
    );

    /*
      9- Agora instanciamos e retornamos o nosso objeto view holder criado lá em cima
      passando como argumento a nossa view criada
    */
    return new MyViewHolder(myListview);
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    //24- Recuperando os itens da lista pela posição do view holder
    Filmes filme = movieList.get(position);

    //25- Definindo os valores dos elementos com os getters criados
    holder.titleText.setText(filme.getTitle());
    holder.yearText.setText(filme.getYear());
    holder.genderText.setText(filme.getGender());

  }

  @Override
  public int getItemCount() {
    //23- Definindo o tamanho do adapter (que será o tamanho da lista)
    return movieList.size();
  }

}
