package com.example.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Activity.ThemMoiThanhVienActivity;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapterThanhVien extends RecyclerView.Adapter<ViewHolderThanhVien> {

    Context context;
    ArrayList<ThanhVien> list;
    SQLiteDB db;

    public RecyclerViewAdapterThanhVien(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderThanhVien onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_thanh_vien,parent,false);
        return new ViewHolderThanhVien(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderThanhVien holder, int position) {
        db = new SQLiteDB(context);

        ThanhVien thanhVien = list.get(position);

        holder.tvTenThanhVien.setText(thanhVien.getHoTen());
        holder.tvNamSinh.setText(thanhVien.getNamSinh());

        holder.row_thanh_vien.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_thanh_vien,null,false);

                EditText edtSuaTenThanhVien = view.findViewById(R.id.edtSuaTenThanhVien);
                EditText edtSuaNamSinhThanhVien = view.findViewById(R.id.edtSuaNamSinhThanhVien);

                edtSuaTenThanhVien.setText(thanhVien.getHoTen());
                edtSuaNamSinhThanhVien.setText(thanhVien.getNamSinh());


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);

                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                view.findViewById(R.id.btnDongYSuaThanhVien).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String hoTen = edtSuaTenThanhVien.getText().toString();
                        String namSinh = edtSuaNamSinhThanhVien.getText().toString();

                        if (hoTen.length()==0 || namSinh.length()==0){
                            Toast.makeText(context, "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        thanhVien.setHoTen(hoTen);
                        thanhVien.setNamSinh(namSinh);

                        db.upDateThanhVien(thanhVien);
                        list.set(position,thanhVien);
                        dialog.dismiss();
                        notifyDataSetChanged();

                    }
                });

                view.findViewById(R.id.btnHuySuaThanhVien).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                return true;
            }
        });

        holder.btnDeleteThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan_delete,null,false);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                view.findViewById(R.id.btnDongYDelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.deleteThanhVien(thanhVien);
                        list.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.btnHuyDelete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
