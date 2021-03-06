package com.example.duanmau.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DoiMatKhauActivity extends AppCompatActivity {

    EditText edtMatKhauCu,edtMatKhauMoi,edtNhapLaiMatKhauMoi;
    SQLiteDB db;
    SharedPreferences sharedPreferences;
    ArrayList<ThuThu> list;

    DrawerLayout drawerLayout;
    TextView tvTenNguoiDung;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        db = new SQLiteDB(this);
        sharedPreferences = getSharedPreferences("sharePrefer",MODE_PRIVATE);
        String tenDangNhap = sharedPreferences.getString("tenDangNhap",null);
        String tenNguoiDung = sharedPreferences.getString("tenNguoiDung",null);
        String matKhau = sharedPreferences.getString("matKhau",null);
        list = db.getAllThuThu();

        edtMatKhauCu = findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi = findViewById(R.id.edtMatKhauMoi);
        edtNhapLaiMatKhauMoi = findViewById(R.id.edtNhapLaiMatKhauMoi);

        findViewById(R.id.btnDongYSuaMatKhau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matKhauCu = edtMatKhauCu.getText().toString();
                String matKhauMoi = edtMatKhauMoi.getText().toString();
                String nhapLaiMatKhauMoi = edtNhapLaiMatKhauMoi.getText().toString();

                if (matKhauCu.length()==0 || matKhauMoi.length() == 0 || nhapLaiMatKhauMoi.length() == 0){
                    Toast.makeText(DoiMatKhauActivity.this, "B???n ph???i ??i???n ?????y ????? th??ng tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!matKhau.equals(matKhauCu)){
                    Toast.makeText(DoiMatKhauActivity.this, "Sai m???t kh???u c??", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (matKhauCu.equals(matKhauMoi)){
                    Toast.makeText(DoiMatKhauActivity.this, "M???t kh???u m???i ph???i kh??c m???t kh???u c??", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!matKhauMoi.equals(nhapLaiMatKhauMoi)){
                    Toast.makeText(DoiMatKhauActivity.this, "M???t kh???u m???i kh??ng ?????ng nh???t", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (int i = 0; i < list.size();i++){
                    ThuThu thuThu = list.get(i);
                    if (thuThu.getPassword().equals(matKhauCu)){
                        thuThu.setPassword(matKhauMoi);
                        int result = db.updateThuThu(thuThu);
                        if (result>0){
                            Toast.makeText(DoiMatKhauActivity.this, "S???a th??nh c??ng", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DoiMatKhauActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(DoiMatKhauActivity.this, "S???a th???t b???i", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        findViewById(R.id.btnReturnDoiMatKhau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}