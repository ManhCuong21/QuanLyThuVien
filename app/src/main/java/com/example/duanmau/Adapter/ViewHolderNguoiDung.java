package com.example.duanmau.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderNguoiDung extends RecyclerView.ViewHolder {

    TextView tvTenNguoiDung,tvTenDangNhapNguoiDung,tvMatKhauNguoiDung;
    ImageView btnDeleteNguoiDung;
    LinearLayout row_nguoi_dung;

    public ViewHolderNguoiDung(@NonNull @NotNull View itemView) {
        super(itemView);
        tvTenNguoiDung = itemView.findViewById(R.id.tvTenNguoiDung);
        tvTenDangNhapNguoiDung = itemView.findViewById(R.id.tvTenDangNhapNguoiDung);
        tvMatKhauNguoiDung = itemView.findViewById(R.id.tvMatKhauNguoiDung);
        btnDeleteNguoiDung = itemView.findViewById(R.id.btnDeleteNguoiDung);
        row_nguoi_dung = itemView.findViewById(R.id.row_nguoi_dung);

    }
}
