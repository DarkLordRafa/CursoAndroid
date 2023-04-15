package com.app.speak_english;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.app.speak_english.fragments.AnimalsFragment;
import com.app.speak_english.fragments.NumbersFragment;
import com.app.speak_english.fragments.VowelsFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity{

  private SmartTabLayout smartTabLayout;
  private ViewPager viewPager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    smartTabLayout = findViewById(R.id.viewPagerTab);
    viewPager = findViewById(R.id.viewPager);

    getSupportActionBar().setElevation(0);

    FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
        getSupportFragmentManager(),
        FragmentPagerItems.with(this)
        .add("Animais", AnimalsFragment.class)
        .add("Números", NumbersFragment.class)
        .add("Vogais", VowelsFragment.class)
        .create()
    );

    viewPager.setAdapter(adapter);
    smartTabLayout.setViewPager(viewPager);
  }
}
