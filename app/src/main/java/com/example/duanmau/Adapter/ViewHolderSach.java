package com.example.duanmau.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderSach extends RecyclerView.ViewHolder {

    TextView tvTenSach,tvTheLoaiSach,tvGiaThueSach;
    ImageView btnDeleteSach;
    LinearLayout row_sach;

    public ViewHolderSach(@NonNull @NotNull View itemView) {
        super(itemView);
        tvTenSach = itemView.findViewById(R.id.tvTenSach);
        tvTheLoaiSach = itemView.findViewById(R.id.tvTheLoaiSach);
        tvGiaThueSach = itemView.findViewById(R.id.tvGiaThueSach);
        btnDeleteSach = itemView.findViewById(R.id.btnDeleteSach);
        row_sach = itemView.findViewById(R.id.row_sach);
    }

}
