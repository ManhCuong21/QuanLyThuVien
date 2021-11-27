package com.example.duanmau.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.duanmau.Adapter.RecyclerViewAdapterPhieuMuon;
import com.example.duanmau.Adapter.RecyclerViewAdapterThongKe;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThongKe;
import com.example.duanmau.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {

    public ThongKeFragment() {
    }


    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerViewAdapterThongKe adapterThongKe;
    SQLiteDB db;
    ArrayList<ThongKe> list;
    RecyclerView recyclerViewThongKe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_thong_ke, container, false);


        db = new SQLiteDB(getContext());
        list = db.getTop10();
        adapterThongKe = new RecyclerViewAdapterThongKe(getContext(),list);
        recyclerViewThongKe = view.findViewById(R.id.recyclerViewThongKe);
        recyclerViewThongKe.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewThongKe.setAdapter(adapterThongKe);
        return view;
    }
}