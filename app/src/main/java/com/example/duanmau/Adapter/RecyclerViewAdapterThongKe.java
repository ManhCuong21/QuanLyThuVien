package com.example.duanmau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Model.ThongKe;
import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapterThongKe extends RecyclerView.Adapter<ViewHolderThongKe> {

    Context context;
    ArrayList<ThongKe> list;

    public RecyclerViewAdapterThongKe(Context context, ArrayList<ThongKe> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderThongKe onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_thong_ke,parent,false);
        return new ViewHolderThongKe(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderThongKe holder, int position) {
        ThongKe thongKe = list.get(position);
        int soThuTu = position+1;
        holder.tvTenSachThongKe.setText(soThuTu+". Sách: "+thongKe.getTenSach());
        holder.tvSoLanMuon.setText("Số lần mượn:"+thongKe.getSoLanMuon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
