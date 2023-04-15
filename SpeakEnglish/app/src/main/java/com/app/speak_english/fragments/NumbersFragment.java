package com.app.speak_english.fragments;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.app.speak_english.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment implements View.OnClickListener{

  private ImageButton number1, number2, number3, number4,number5, number6;
  private MediaPlayer mediaPlayer;


  public NumbersFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_numbers, container, false);

    number1 = view.findViewById(R.id.number1);
    number2 = view.findViewById(R.id.number2);
    number3 = view.findViewById(R.id.number3);
    number4 = view.findViewById(R.id.number4);
    number5 = view.findViewById(R.id.number5);
    number6 = view.findViewById(R.id.number6);

    number1.setOnClickListener(this);
    number2.setOnClickListener(this);
    number3.setOnClickListener(this);
    number4.setOnClickListener(this);
    number5.setOnClickListener(this);
    number6.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.number1:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.one);
        playAudio();
        break;
      case R.id.number2:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.two);
        playAudio();
        break;
      case R.id.number3:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.three);
        playAudio();
        break;
      case R.id.number4:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.four);
        playAudio();
        break;
      case R.id.number5:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.five);
        playAudio();
        break;
      case R.id.number6:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.six);
        playAudio();
        break;
    }
  }

  public void playAudio() {
    if (mediaPlayer != null) {
      mediaPlayer.start();

      mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
          mediaPlayer.release();
        }
      });
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    if (mediaPlayer != null){
      mediaPlayer.release();
      mediaPlayer = null;
    }
  }

}