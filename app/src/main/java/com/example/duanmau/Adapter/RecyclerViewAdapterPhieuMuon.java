package com.example.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapterPhieuMuon extends RecyclerView.Adapter<ViewHolderPhieuMuon> {

    Context context;
    ArrayList<PhieuMuon> list;

    public RecyclerViewAdapterPhieuMuon(Context context, ArrayList<PhieuMuon> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderPhieuMuon onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_phieu_muon,parent,false);
        return new ViewHolderPhieuMuon(view);
    }

    SQLiteDB db;
    ArrayList<ThanhVien> list_thanh_vien;
    ArrayList<Sach> list_sach;
    ArrayList<String> list_thanh_vien_2,list_sach_2;
    int maTv,id_sach,tra_sach;

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderPhieuMuon holder, int position) {

        PhieuMuon phieuMuon = list.get(position);

        holder.tvThanhVien.setText(phieuMuon.getTenThanhVien());
        holder.tvSachPhieuMuon.setText("Sách: "+phieuMuon.getTenSach());
        holder.tvGiaThueSach.setText(String.valueOf("Giá: "+phieuMuon.getGiaThue()));
        String ngayThue = phieuMuon.getNgayThue();
        String year = ngayThue.substring(0,4);
        String month = ngayThue.substring(5,7);
        String day = ngayThue.substring(8);
        String date = day+"/"+month+"/"+year;
        holder.tvNgayThuePhieuMuon.setText(date);
        if (phieuMuon.getTraSach() == 0){
            holder.tvTinhTrang.setText("Chưa trả sách");
            holder.tvTinhTrang.setTextColor(context.getResources().getColor(R.color.red));
        }else {
            holder.tvTinhTrang.setText("Đã trả sách");
            holder.tvTinhTrang.setTextColor(context.getResources().getColor(R.color.green));
        }

        db = new SQLiteDB(context);

        holder.row_phieu_muon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_phieu_muon,null,false);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                AutoCompleteTextView edtSuaThanhVienPhieuMuon = view.findViewById(R.id.edtSuaThanhVienPhieuMuon);
                AutoCompleteTextView edtSuaSachPhieuMuon = view.findViewById(R.id.edtSuaSachPhieuMuon);
                EditText edtSuaGiaThuePhieuMuon = view.findViewById(R.id.edtSuaGiaThuePhieuMuon);
                EditText edtSuaNgayThuePhieuMuon = view.findViewById(R.id.edtSuaNgayThuePhieuMuon);
                CheckBox ckbDaTraSach = view.findViewById(R.id.ckbDaTraSach);
                Button btnHuySuaPhieuMuon = view.findViewById(R.id.btnHuySuaPhieuMuon);
                Button btnDongYSuaPhieuMuon = view.findViewById(R.id.btnDongYSuaPhieuMuon);

                edtSuaThanhVienPhieuMuon.setText(phieuMuon.getTenThanhVien());
                edtSuaSachPhieuMuon.setText(phieuMuon.getTenSach());
                edtSuaGiaThuePhieuMuon.setText(String.valueOf(phieuMuon.getGiaThue()));
                edtSuaNgayThuePhieuMuon.setText(date);
                maTv = phieuMuon.getMaTv();
                id_sach = phieuMuon.getIdSach();
                tra_sach = phieuMuon.getTraSach();
                if (tra_sach == 1){
                    ckbDaTraSach.setChecked(true);

                }else {
                    ckbDaTraSach.setChecked(false);
                }

                list_thanh_vien = db.getAllThanhVien();
                list_sach = db.getAllSach();

                list_thanh_vien_2 = new ArrayList<>();
                list_sach_2 = new ArrayList<>();

                for (int i = 0; i < list_thanh_vien.size();i++){
                    ThanhVien thanhVien = list_thanh_vien.get(i);
                    list_thanh_vien_2.add(thanhVien.getHoTen());
                }

                for (int i = 0; i < list_sach.size();i++){
                    Sach sach = list_sach.get(i);
                    list_sach_2.add(sach.getTenSach());
                }

                edtSuaThanhVienPhieuMuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ThanhVien thanhVien = list_thanh_vien.get(position);
                        maTv = thanhVien.getMaTv();
                    }
                });

                edtSuaSachPhieuMuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Sach sach = list_sach.get(position);
                        edtSuaGiaThuePhieuMuon.setText(String.valueOf(sach.getGiaThue()));
                        id_sach = sach.getId();
                    }
                });



                btnDongYSuaPhieuMuon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ckbDaTraSach.isChecked()){
                            tra_sach = 1;
                        }else {
                            tra_sach = 0;
                        }

                        phieuMuon.setMaTv(maTv);
                        phieuMuon.setTenThanhVien(edtSuaThanhVienPhieuMuon.getText().toString());
                        phieuMuon.setIdSach(id_sach);
                        phieuMuon.setTenSach(edtSuaSachPhieuMuon.getText().toString());
                        phieuMuon.setGiaThue(Integer.parseInt(edtSuaGiaThuePhieuMuon.getText().toString()));
                        phieuMuon.setNgayThue(phieuMuon.getNgayThue());
                        phieuMuon.setTraSach(tra_sach);

                        db.updatePhieuMuon(phieuMuon);
                        list.set(position,phieuMuon);
                        notifyDataSetChanged();
                        dialog.dismiss();

                    }
                });

                btnHuySuaPhieuMuon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ArrayAdapter adapter1 = new ArrayAdapter(context,R.layout.row_list_item,list_thanh_vien_2);
                edtSuaThanhVienPhieuMuon.setAdapter(adapter1);
                ArrayAdapter adapter2 = new ArrayAdapter(context,R.layout.row_list_item,list_sach_2);
                edtSuaSachPhieuMuon.setAdapter(adapter2);

                return true;
            }
        });

        holder.btnDeletePhieuMuon.setOnClickListener(new View.OnClickListener() {
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
                        db.deletePhieuMuon(phieuMuon);
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
