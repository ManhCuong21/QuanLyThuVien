package com.example.duanmau.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

public class ViewHolderTheLoai extends RecyclerView.ViewHolder {

    TextView tvTheLoai;
    ImageView btnDelete;
    LinearLayout row_the_loai;

    public ViewHolderTheLoai(@NonNull @NotNull View itemView) {
        super(itemView);
        tvTheLoai = itemView.findViewById(R.id.tvTheLoai);
        btnDelete = itemView.findViewById(R.id.btnDeleteTheLoai);
        row_the_loai = itemView.findViewById(R.id.row_the_loai);

    }
}
