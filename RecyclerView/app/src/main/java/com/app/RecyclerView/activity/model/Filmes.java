package com.app.RecyclerView.activity.model;

//12- Criamos nossa classe de filme com as variáveis que queremos
public class Filmes {

  private String title;
  private String year;
  private String gender;

  //13- Criamos um construtor para essas variáveis (Ou getters e setters)
  public Filmes(String title, String year, String gender) {
    this.title = title;
    this.year = year;
    this.gender = gender;
  }

  //13.2- Criando getters e setters
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }
}
