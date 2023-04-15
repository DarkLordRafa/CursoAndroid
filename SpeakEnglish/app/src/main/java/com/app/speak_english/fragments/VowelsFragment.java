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
public class VowelsFragment extends Fragment implements View.OnClickListener{

  private ImageButton a, e, i, o, u;
  private MediaPlayer mediaPlayer;

  public VowelsFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_vowels, container, false);

    a = view.findViewById(R.id.a);
    e = view.findViewById(R.id.e);
    i = view.findViewById(R.id.i);
    o = view.findViewById(R.id.o);
    u = view.findViewById(R.id.u);

    a.setOnClickListener(this);
    e.setOnClickListener(this);
    i.setOnClickListener(this);
    o.setOnClickListener(this);
    u.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.a:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.a_sound);
        playAudio();
        break;
      case R.id.e:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.e_sound);
        playAudio();
        break;
      case R.id.i:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.i_sound);
        playAudio();
        break;
      case R.id.o:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.o_sound);
        playAudio();
        break;
      case R.id.u:
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.u_sound);
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
