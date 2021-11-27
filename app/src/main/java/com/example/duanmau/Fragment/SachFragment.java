package com.example.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.duanmau.R;
import com.example.duanmau.Adapter.RecyclerViewAdapterSach;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Activity.ThemMoiSachActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SachFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SachFragment extends Fragment {

    public SachFragment() {

    }

    public static SachFragment newInstance() {
        SachFragment fragment = new SachFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button btnThemSach;
    SQLiteDB db;
    RecyclerViewAdapterSach adapter;
    ArrayList<Sach> list;
    RecyclerView recyclerViewSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sach, container, false);

        db = new SQLiteDB(getContext());

        btnThemSach = view.findViewById(R.id.btnThemSach);
        btnThemSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThemMoiSachActivity.class);
                startActivity(intent);
            }
        });

        list = db.getAllSach();
        adapter = new RecyclerViewAdapterSach(getContext(),list);
        recyclerViewSach = view.findViewById(R.id.recyclerViewSach);
        recyclerViewSach.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSach.setAdapter(adapter);

        return view;
    }
}