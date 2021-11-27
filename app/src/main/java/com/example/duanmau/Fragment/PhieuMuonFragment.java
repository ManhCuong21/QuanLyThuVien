package com.example.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.Activity.ThemMoiPhieuMuonActivity;
import com.example.duanmau.Adapter.RecyclerViewAdapterPhieuMuon;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhieuMuonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhieuMuonFragment extends Fragment {


    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    public static PhieuMuonFragment newInstance() {
        PhieuMuonFragment fragment = new PhieuMuonFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SQLiteDB db;
    RecyclerViewAdapterPhieuMuon adapter;
    ArrayList<PhieuMuon> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        view.findViewById(R.id.btnThemPhieuMuon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThemMoiPhieuMuonActivity.class);
                startActivity(intent);
            }
        });

        db = new SQLiteDB(getContext());
        list = db.getAllPhieuMuon();
        adapter = new RecyclerViewAdapterPhieuMuon(getContext(),list);
        RecyclerView recyclerViewPhieuMuon = view.findViewById(R.id.recyclerViewPhieuMuon);
        recyclerViewPhieuMuon.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPhieuMuon.setAdapter(adapter);

        return view;
    }
}