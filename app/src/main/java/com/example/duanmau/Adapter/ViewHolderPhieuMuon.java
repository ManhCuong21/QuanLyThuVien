package com.example.duanmau.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;



public class ViewHolderPhieuMuon extends RecyclerView.ViewHolder {

    TextView tvThanhVien,tvSachPhieuMuon,tvTinhTrang,tvGiaThueSach,tvNgayThuePhieuMuon;
    ImageView btnDeletePhieuMuon;
    LinearLayout row_phieu_muon;

    public ViewHolderPhieuMuon(@NonNull @NotNull View itemView) {
        super(itemView);
        tvThanhVien = itemView.findViewById(R.id.tvThanhVien);
        tvSachPhieuMuon = itemView.findViewById(R.id.tvSachPhieuMuon);
        tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
        tvGiaThueSach = itemView.findViewById(R.id.tvGiaThueSach);
        tvNgayThuePhieuMuon = itemView.findViewById(R.id.tvNgayThuePhieuMuon);
        btnDeletePhieuMuon = itemView.findViewById(R.id.btnDeletePhieuMuon);
        row_phieu_muon = itemView.findViewById(R.id.row_phieu_muon);
    }
}
