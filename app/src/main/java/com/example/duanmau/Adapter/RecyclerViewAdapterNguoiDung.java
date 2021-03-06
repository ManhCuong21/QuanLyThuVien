package com.example.duanmau.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.Activity.ThemMoiNguoiDungActivity;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RecyclerViewAdapterNguoiDung extends RecyclerView.Adapter<ViewHolderNguoiDung> {

    Context context;
    ArrayList<ThuThu> list;

    public RecyclerViewAdapterNguoiDung(Context context, ArrayList<ThuThu> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolderNguoiDung onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_nguoi_dung,parent,false);
        return new ViewHolderNguoiDung(view);
    }

    SQLiteDB db;

    EditText edtSuaTenNguoiDung,edtSuaTenDangNhap,edtSuaMatKhauNguoiDung,edtNhapLaiMatKhauNguoidung;

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderNguoiDung holder, int position) {

        ThuThu thuThu = list.get(position);
        holder.tvTenNguoiDung.setText(thuThu.getName());
        holder.tvTenDangNhapNguoiDung.setText(thuThu.getMaTt());
        holder.tvMatKhauNguoiDung.setText(thuThu.getPassword());


        db = new SQLiteDB(context);

        holder.row_nguoi_dung.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_nguoi_dung,null,false);

                edtSuaTenNguoiDung = view.findViewById(R.id.edtSuaTenNguoiDung);
                edtSuaTenDangNhap = view.findViewById(R.id.edtSuaTenDangNhap);
                edtSuaMatKhauNguoiDung = view.findViewById(R.id.edtSuaMatKhauNguoiDung);
                edtNhapLaiMatKhauNguoidung = view.findViewById(R.id.edtNhapLaiMatKhauNguoidung);

                edtSuaTenNguoiDung.setText(thuThu.getName());
                edtSuaTenDangNhap.setText(thuThu.getMaTt());
                edtSuaMatKhauNguoiDung.setText(thuThu.getPassword());
                edtNhapLaiMatKhauNguoidung.setText(thuThu.getPassword());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(view);

                Dialog dialog = builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                view.findViewById(R.id.btnDongYSuaNguoiDung).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (kiemTraNhap() == false){
                            return;
                        }

                        String tenNguoiDung = edtSuaTenNguoiDung.getText().toString();
                        String tenDangNhap = edtSuaTenDangNhap.getText().toString();
                        String matKhau = edtSuaMatKhauNguoiDung.getText().toString();

                        ThuThu nguoiDung = new ThuThu();
                        nguoiDung.setName(tenNguoiDung);
                        nguoiDung.setMaTt(tenDangNhap);
                        nguoiDung.setPassword(matKhau);

                        long result = db.updateThuThu(nguoiDung);
                        if (result>0){
                            Toast.makeText(context, "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "S???a th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                        list.set(position,nguoiDung);
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                });

                view.findViewById(R.id.btnHuySuaNguoiDung).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                return true;
            }
        });

        holder.btnDeleteNguoiDung.setOnClickListener(new View.OnClickListener() {
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
                        db.deleteThuThu(thuThu);
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

    private boolean kiemTraNhap(){

        if(edtSuaTenNguoiDung.getText().toString().length() == 0){
            Toast.makeText(context, "Ch??a nh???p t??n ng?????i d??ng", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(edtSuaTenDangNhap.getText().toString().length() == 0){
            Toast.makeText(context, "Ch??a nh???p t??n ????ng nh???p", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtSuaMatKhauNguoiDung.getText().toString().length() == 0){
            Toast.makeText(context, "Ch??a nh???p m???t kh???u", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtNhapLaiMatKhauNguoidung.getText().toString().length() == 0){
            Toast.makeText(context, "Ch??a nh???p l???i m???t kh???u", Toast.LENGTH_SHORT).show();
            return false;
        }
        String password1 = edtSuaMatKhauNguoiDung.getText().toString();
        String enterPass = edtNhapLaiMatKhauNguoidung.getText().toString();

        if(password1.compareTo(enterPass) != 0){
            Toast.makeText(context, "M???t kh???u kh??ng ?????ng nh???t", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
