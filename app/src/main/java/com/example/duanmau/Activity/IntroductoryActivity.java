package com.example.duanmau.Activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.airbnb.lottie.LottieAnimationView;
import com.cuberto.liquid_swipe.LiquidPager;
import com.example.duanmau.Fragment.OnBoarding1Fragment;
import com.example.duanmau.Fragment.OnBoarding2Fragment;
import com.example.duanmau.Fragment.OnBoarding3Fragment;
import com.example.duanmau.R;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView logo,splashImg;
    TextView appName,appWelcome;
    LottieAnimationView lottieAnimationView;
    private static final int NUM_PAGES = 3;
    private LiquidPager Pager;
    private ScreenSlidePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_introductory);

        logo = findViewById(R.id.logo1);
        appWelcome = findViewById(R.id.app_welcome);
        appName = findViewById(R.id.app_name);
        lottieAnimationView = findViewById(R.id.lottie);
        splashImg = findViewById(R.id.img1);

        Pager = findViewById(R.id.viewpager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        Pager.setAdapter(pagerAdapter);

        splashImg.animate().translationY(-5000).setDuration(1500).setStartDelay(4000);
        logo.animate().translationY(5000).setDuration(1500).setStartDelay(4000);
        appWelcome.animate().translationY(5000).setDuration(1500).setStartDelay(4000);
        appName.animate().translationY(5000).setDuration(1500).setStartDelay(4000);
        lottieAnimationView.animate().translationY(5000).setDuration(1500).setStartDelay(4000);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new OnBoarding1Fragment();

                case 1:
                    return new OnBoarding2Fragment();

                case 2:
                    return new OnBoarding3Fragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}