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
public class AnimalsFragment extends Fragment implements View.OnClickListener{

  private ImageButton animal1, animal2, animal3, animal4,animal5, animal6;
  private MediaPlayer mediaPlayer;


  public AnimalsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_animals, container, false);

    animal1 = view.findViewById(R.id.animal1);
    animal2 = view.findViewById(R.id.animal2);
    animal3 = view.findViewById(R.id.animal3);
    animal4 = view.findViewById(R.id.animal4);
    animal5 = view.findViewById(R.id.animal5);
    animal6 = view.findViewById(R.id.animal6);

    animal1.setOnClickListener(this);
    animal2.setOnClickListener(this);
    animal3.setOnClickListener(this);
    animal4.setOnClickListener(this);
    animal5.setOnClickListener(this);
    animal6.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.animal1:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.dog);
        playAudio();
        break;
      case R.id.animal2:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cat);
        playAudio();
        break;
      case R.id.animal3:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.lion);
        playAudio();
        break;
      case R.id.animal4:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.monkey);
        playAudio();
        break;
      case R.id.animal5:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.sheep);
        playAudio();
        break;
      case R.id.animal6:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.cow);
        playAudio();
        break;
    }
  }

  public void playAudio(){
    if (mediaPlayer != null){
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
