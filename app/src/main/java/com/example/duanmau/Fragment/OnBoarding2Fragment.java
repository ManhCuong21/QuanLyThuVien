package com.example.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoarding2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoarding2Fragment extends Fragment {

    public OnBoarding2Fragment() {
    }



    public static OnBoarding2Fragment newInstance() {
        OnBoarding2Fragment fragment = new OnBoarding2Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding2, container, false);
        return root;
    }
}