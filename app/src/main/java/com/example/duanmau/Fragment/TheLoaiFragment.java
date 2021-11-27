package com.example.duanmau.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.duanmau.Activity.TheLoaiActivity;
import com.example.duanmau.R;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Adapter.RecyclerViewAdapterTheLoai;
import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.Activity.ThemMoiTheLoaiActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TheLoaiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TheLoaiFragment extends Fragment {

    public TheLoaiFragment() {

    }


    public static TheLoaiFragment newInstance() {
        TheLoaiFragment fragment = new TheLoaiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Button btnThemTheLoai;
    SQLiteDB db;
    RecyclerViewAdapterTheLoai adapter;
    ArrayList<TheLoai> list;
    RecyclerView recyclerViewTheLoai;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_the_loai, container, false);

        db = new SQLiteDB(getContext());




        btnThemTheLoai = view.findViewById(R.id.btnThemTheLoai);
        btnThemTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThemMoiTheLoaiActivity.class);
                startActivity(intent);
            }
        });

        list = db.getAllTheLoai();
        adapter = new RecyclerViewAdapterTheLoai(getContext(),list);
        recyclerViewTheLoai = view.findViewById(R.id.recyclerViewTheLoai);
        recyclerViewTheLoai.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTheLoai.setAdapter(adapter);

        return view;
    }

}