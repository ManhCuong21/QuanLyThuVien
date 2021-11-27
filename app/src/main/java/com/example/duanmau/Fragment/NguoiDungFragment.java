package com.example.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.duanmau.Activity.ThemMoiNguoiDungActivity;
import com.example.duanmau.Adapter.RecyclerViewAdapterNguoiDung;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NguoiDungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NguoiDungFragment extends Fragment {


    public NguoiDungFragment() {
        // Required empty public constructor
    }
    public static NguoiDungFragment newInstance() {
        NguoiDungFragment fragment = new NguoiDungFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SQLiteDB db;
    RecyclerView recyclerViewNguoiDung;
    RecyclerViewAdapterNguoiDung adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);

        view.findViewById(R.id.btnThemNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThemMoiNguoiDungActivity.class);
                startActivity(intent);
            }
        });

        db = new SQLiteDB(getContext());
        ArrayList<ThuThu> list = db.getAllThuThu();
        adapter = new RecyclerViewAdapterNguoiDung(getContext(),list);
        recyclerViewNguoiDung = view.findViewById(R.id.recyclerViewNguoiDung);
        recyclerViewNguoiDung.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewNguoiDung.setAdapter(adapter);

        return view;
    }
}