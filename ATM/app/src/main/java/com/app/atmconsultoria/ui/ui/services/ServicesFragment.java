package com.app.atmconsultoria.ui.ui.services;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.atmconsultoria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {


  public ServicesFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_services, container, false);
  }

}
