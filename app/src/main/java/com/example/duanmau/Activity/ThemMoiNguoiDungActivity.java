package com.example.duanmau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.R;

import java.util.ArrayList;

public class ThemMoiNguoiDungActivity extends AppCompatActivity {

    EditText edtThemMoiTenNguoiDung,edtThemMoiTenDangNhap,edtThemMoiMatKhauNguoiDung,edtNhapLaiMatKhauNguoidung;
    SQLiteDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi_nguoi_dung);

        edtThemMoiTenNguoiDung = findViewById(R.id.edtThemMoiTenNguoiDung);
        edtThemMoiTenDangNhap = findViewById(R.id.edtThemMoiTenDangNhap);
        edtThemMoiMatKhauNguoiDung = findViewById(R.id.edtThemMoiMatKhauNguoiDung);
        edtNhapLaiMatKhauNguoidung = findViewById(R.id.edtNhapLaiMatKhauNguoidung);

        db = new SQLiteDB(this);

        findViewById(R.id.btnThemMoiNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraNhap() == false){
                    return;
                }

                String tenNguoiDung = edtThemMoiTenNguoiDung.getText().toString();
                String tenDangNhap = edtThemMoiTenDangNhap.getText().toString();
                String matKhau = edtThemMoiMatKhauNguoiDung.getText().toString();

                ThuThu nguoiDung = new ThuThu();
                nguoiDung.setName(tenNguoiDung);
                nguoiDung.setMaTt(tenDangNhap);
                nguoiDung.setPassword(matKhau);

                long result = db.insertThuThu(nguoiDung);
                if (result > 0){
                    Toast.makeText(ThemMoiNguoiDungActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemMoiNguoiDungActivity.this,NguoiDungActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ThemMoiNguoiDungActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnReturnNguoiDung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private boolean kiemTraNhap(){
        ArrayList<ThuThu> list = db.getAllThuThu();


        if(edtThemMoiTenNguoiDung.getText().toString().length() == 0){
            Toast.makeText(ThemMoiNguoiDungActivity.this, "Chưa nhập tên người dùng", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(edtThemMoiTenDangNhap.getText().toString().length() == 0){
            Toast.makeText(ThemMoiNguoiDungActivity.this, "Chưa nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtThemMoiMatKhauNguoiDung.getText().toString().length() == 0){
            Toast.makeText(ThemMoiNguoiDungActivity.this, "Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtNhapLaiMatKhauNguoidung.getText().toString().length() == 0){
            Toast.makeText(ThemMoiNguoiDungActivity.this, "Chưa nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        String password1 = edtThemMoiMatKhauNguoiDung.getText().toString();
        String enterPass = edtNhapLaiMatKhauNguoidung.getText().toString();

        if(password1.compareTo(enterPass) != 0){
            Toast.makeText(ThemMoiNguoiDungActivity.this, "Mật khẩu không đồng nhất", Toast.LENGTH_SHORT).show();
            return false;
        }

        for(int i = 0; i < list.size();i++){
            String username = list.get(i).getMaTt();
            if (edtThemMoiTenDangNhap.getText().toString().equals(username)){
                Toast.makeText(ThemMoiNguoiDungActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }
}