package com.example.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.Activity.ThemMoiThanhVienActivity;
import com.example.duanmau.Adapter.RecyclerViewAdapterThanhVien;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThanhVienFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThanhVienFragment extends Fragment {


    public ThanhVienFragment() {
        // Required empty public constructor
    }

    public static ThanhVienFragment newInstance() {
        ThanhVienFragment fragment = new ThanhVienFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SQLiteDB db;
    RecyclerView recyclerViewThanhVien;
    RecyclerViewAdapterThanhVien adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);

        view.findViewById(R.id.btnThemThanhVien).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThemMoiThanhVienActivity.class);
                startActivity(intent);
            }
        });

        db = new SQLiteDB(getContext());
        ArrayList<ThanhVien> list = db.getAllThanhVien();
        adapter = new RecyclerViewAdapterThanhVien(getContext(),list);
        recyclerViewThanhVien = view.findViewById(R.id.recyclerViewThanhVien);
        recyclerViewThanhVien.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewThanhVien.setAdapter(adapter);

        return view;
    }
}