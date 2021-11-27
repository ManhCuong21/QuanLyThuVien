package com.example.duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.duanmau.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnBoarding1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnBoarding1Fragment extends Fragment {




    public OnBoarding1Fragment() {
        // Required empty public constructor
    }


    public static OnBoarding1Fragment newInstance(String param1, String param2) {
        OnBoarding1Fragment fragment = new OnBoarding1Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_on_boarding1, container, false);
        return root;
    }
}