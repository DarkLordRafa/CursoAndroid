package com.app.atmconsultoria.ui.ui.about;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.atmconsultoria.R;

import java.security.acl.Group;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


  public AboutFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    //return inflater.inflate(R.layout.fragment_about, container, false);
    Element version = new Element();
    version.setTitle("Versão 1.0");

    View aboutView = new AboutPage(getActivity())
        .setImage(R.drawable.logo)
        .setDescription("A ATM Consultoria tem como missão apoiar organizações" +
            " que desejam alcançar o sucesso através da excelência em gestão e da " +
            "busca pela qualidade.")
        .addGroup("Entre em contato")
        .addEmail("atmconsultoriaemail@gmail.com", "Nos envie um email")
        .addWebsite("atmconsultoria.com.br", "Visite nosso site")
        .addGroup("Redes sociais")
        .addGitHub("DarkLordRafa", "Veja nosso Github")
        .addWebsite("https://www.linkedin.com/in/rafael-silva-363bbb212/",
            "Veja nosso LinkedIn")
        .addFacebook("Rafathefroster", "Visite nosso Facebook")
        .addInstagram("darklordrafa", "Nos siga no Instagram")
        .addPlayStore("com.facebook.katana", "Baixe nosso app parceiro: Facebook")
        .addItem(version)
        .create();
    return aboutView;
  }

}
