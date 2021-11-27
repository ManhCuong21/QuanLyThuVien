package com.example.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Activity.ThemMoiSachActivity;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.R;
import com.example.duanmau.Database.SQLiteDB;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapterSach extends RecyclerView.Adapter<ViewHolderSach> {

    Context context;
    ArrayList<Sach> list;
    ArrayList<String> list_the_loai;
    SQLiteDB db;

    public RecyclerViewAdapterSach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderSach onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_sach,parent,false);
        return new ViewHolderSach(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderSach holder, int position) {
        db = new SQLiteDB(context);

        Sach sach = list.get(position);
        holder.tvTenSach.setText(sach.getTenSach());
        holder.tvTheLoaiSach.setText(sach.getTheLoai());
        holder.tvGiaThueSach.setText(String.valueOf(sach.getGiaThue()));

        list_the_loai = new ArrayList<>();
        ArrayList<TheLoai> theLoaiArrayList = db.getAllTheLoai();

        for(int i =0;i < theLoaiArrayList.size();i++){
            String tenTheLoai = theLoaiArrayList.get(i).getTenTheLoai();
            list_the_loai.add(tenTheLoai);
        }

        holder.row_sach.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_sach,null,false);

                AutoCompleteTextView edtSuaTheLoaiSach = view.findViewById(R.id.edtSuaTheLoaiSach);


                EditText edtSuaTenSach = view.findViewById(R.id.edtSuaTenSach);
                EditText edtSuaGiaThue = view.findViewById(R.id.edtSuaGiaThue);
                edtSuaTenSach.setText(sach.getTenSach());
                edtSuaGiaThue.setText(String.valueOf(sach.getGiaThue()));
                edtSuaTheLoaiSach.setText(sach.getTheLoai());

                ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,list_the_loai);
                edtSuaTheLoaiSach.setAdapter(adapter);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                view.findViewById(R.id.btnDongYSuaSach).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String tenSach = edtSuaTenSach.getText().toString();
                        int giaThue = Integer.parseInt(edtSuaGiaThue.getText().toString());
                        String theLoai = edtSuaTheLoaiSach.getText().toString();

                        if (tenSach.length() == 0 || String.valueOf(giaThue).length() == 0 || theLoai.length()==0){
                            Toast.makeText(context, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        int id_the_loai = 0;
                        for (int i = 0; i < theLoaiArrayList.size();i++){
                            String tenTheLoai = theLoaiArrayList.get(i).getTenTheLoai();
                            if (theLoai.equals(tenTheLoai)){
                                id_the_loai = theLoaiArrayList.get(i).getId();
                            }
                        }

                        sach.setTenSach(tenSach);
                        sach.setGiaThue(giaThue);
                        sach.setIdTheLoai(id_the_loai);
                        sach.setTheLoai(theLoai);
                        db.updateSach(sach);
                        dialog.dismiss();
                        list.set(position,sach);
                        notifyDataSetChanged();



                    }
                });

                view.findViewById(R.id.btnHuySuaSach).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                return true;
            }
        });

        holder.btnDeleteSach.setOnClickListener(new View.OnClickListener() {
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
                        db.deleteSach(sach);
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
