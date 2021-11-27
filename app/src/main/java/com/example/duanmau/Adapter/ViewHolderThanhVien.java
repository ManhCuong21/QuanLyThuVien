package com.example.duanmau.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderThanhVien extends RecyclerView.ViewHolder {

    TextView tvTenThanhVien,tvNamSinh;
    ImageView btnDeleteThanhVien;
    LinearLayout row_thanh_vien;

    public ViewHolderThanhVien(@NonNull @NotNull View itemView) {
        super(itemView);
        tvTenThanhVien = itemView.findViewById(R.id.tvTenThanhVien);
        tvNamSinh = itemView.findViewById(R.id.tvNamSinh);
        btnDeleteThanhVien = itemView.findViewById(R.id.btnDeleteThanhVien);
        row_thanh_vien = itemView.findViewById(R.id.row_thanh_vien);
    }
}
