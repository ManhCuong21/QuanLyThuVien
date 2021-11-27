package com.example.duanmau.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderThongKe extends RecyclerView.ViewHolder {

    TextView tvTenSachThongKe,tvSoLanMuon;

    public ViewHolderThongKe(@NonNull @NotNull View itemView) {
        super(itemView);
        tvTenSachThongKe = itemView.findViewById(R.id.tvTenSachThongKe);
        tvSoLanMuon = itemView.findViewById(R.id.tvSoLanMuon);
    }
}
