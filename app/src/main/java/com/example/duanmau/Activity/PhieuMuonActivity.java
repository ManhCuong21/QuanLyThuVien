package com.example.duanmau.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.duanmau.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class PhieuMuonActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    TextView tvTenNguoiDung;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muon);

        sharedPreferences = getSharedPreferences("sharePrefer", MODE_PRIVATE);
        String tenDangNhap = sharedPreferences.getString("tenDangNhap", null);
        String tenNguoiDung = sharedPreferences.getString("tenNguoiDung", null);

        navigationView = findViewById(R.id.navigation_view);
        if (tenDangNhap.equalsIgnoreCase("admin")) {
            navigationView.inflateMenu(R.menu.navigation_view_admin);
        } else {
            navigationView.inflateMenu(R.menu.navigation_view_user);
        }
        navigationView.setItemIconTintList(null);

        View headerView = navigationView.getHeaderView(0);
        tvTenNguoiDung = headerView.findViewById(R.id.tvTenNguoiDung);
        tvTenNguoiDung.setText(tenNguoiDung);

        drawerLayout = findViewById(R.id.drawer_layout);
        click(this);

        findViewById(R.id.img_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void click(Context context) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.trang_chu_activity:
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.phieu_muon_activity:
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.the_loai_activity:
                        Intent intent3 = new Intent(context, TheLoaiActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.sach_activity:
                        Intent intent4 = new Intent(context, SachActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.thanh_vien_activity:
                        Intent intent5 = new Intent(context, ThanhVienActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.sach_muon_nhieu_nhat_activity:
                        Intent intent6 = new Intent(context, ThongKeActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.doanh_thu_activity:
                        Intent intent7 = new Intent(context, DoanhThuActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.nguoi_dung_activity:
                        Intent intent8 = new Intent(context, NguoiDungActivity.class);
                        startActivity(intent8);
                        break;
                    case R.id.doi_mat_khau_activity:
                        Intent intent9 = new Intent(context, DoiMatKhauActivity.class);
                        startActivity(intent9);
                        break;
                    case R.id.dang_xuat_activity:
                        Intent intent10 = new Intent(context, LoginActivity.class);
                        startActivity(intent10);
                        break;
                }
                return true;
            }
        });
    }
}