package com.example.duanmau.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duanmau.Activity.DoanhThuActivity;
import com.example.duanmau.Activity.NguoiDungActivity;
import com.example.duanmau.Activity.PhieuMuonActivity;
import com.example.duanmau.Activity.ThongKeActivity;
import com.example.duanmau.R;
import com.example.duanmau.Activity.SachActivity;
import com.example.duanmau.Activity.TheLoaiActivity;
import com.example.duanmau.Activity.ThanhVienActivity;


public class HomeFragment extends Fragment {


    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    CardView the_loai_activity,sach_activity,thanh_vien_activity,phieu_muon_activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        the_loai_activity = view.findViewById(R.id.the_loai_activity);
        the_loai_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TheLoaiActivity.class);
                startActivity(intent);
            }
        });

        sach_activity = view.findViewById(R.id.sach_activity);
        sach_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SachActivity.class);
                startActivity(intent);
            }
        });

        thanh_vien_activity = view.findViewById(R.id.thanh_vien_activity);
        thanh_vien_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThanhVienActivity.class);
                startActivity(intent);
            }
        });

        phieu_muon_activity = view.findViewById(R.id.phieu_muon_activity);
        phieu_muon_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PhieuMuonActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.nguoi_dung_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NguoiDungActivity.class);
                startActivity(intent);
            }
        });

        view.findViewById(R.id.doanh_thu_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DoanhThuActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}