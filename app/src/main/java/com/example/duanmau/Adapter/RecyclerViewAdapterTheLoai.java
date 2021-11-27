package com.example.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Activity.ThemMoiTheLoaiActivity;
import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.R;
import com.example.duanmau.Database.SQLiteDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterTheLoai extends RecyclerView.Adapter<ViewHolderTheLoai> {

    Context context;
    ArrayList<TheLoai> list;
    SQLiteDB db;

    public RecyclerViewAdapterTheLoai(Context context, ArrayList<TheLoai> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderTheLoai onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_the_loai,parent,false);
        return new ViewHolderTheLoai(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderTheLoai holder, int position) {

        db = new SQLiteDB(context);

        TheLoai theLoai = list.get(position);
        holder.tvTheLoai.setText(theLoai.getTenTheLoai());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan_delete,null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Button btnDongYDeleteTheLoai = view.findViewById(R.id.btnDongYDelete);
                btnDongYDeleteTheLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.deleteTheLoai(theLoai);
                        list.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                Button btnHuyDeleteTheLoai = view.findViewById(R.id.btnHuyDelete);
                btnHuyDeleteTheLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        holder.row_the_loai.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_the_loai,null);
                EditText edtSuaTheLoai = view.findViewById(R.id.edtSuaTheLoai);
                edtSuaTheLoai.setText(theLoai.getTenTheLoai());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                Button btnHuySuaTheLoai = view.findViewById(R.id.btnHuySuaTheLoai);
                btnHuySuaTheLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                Button btnDongYSuaTheLoai = view.findViewById(R.id.btnDongYSuaTheLoai);
                btnDongYSuaTheLoai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tenTheLoai = edtSuaTheLoai.getText().toString();
                        if (tenTheLoai.length()==0){
                            Toast.makeText(context, "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        theLoai.setTenTheLoai(tenTheLoai);
                        db.updateTheLoai(theLoai);
                        list.set(position,theLoai);
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
