package com.example.duanmau.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.duanmau.Adapter.RecyclerViewAdapterTheLoai;
import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.R;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TheLoaiActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    TextView tvTenNguoiDung;
    NavigationView navigationView;
    SQLiteDB db;
    RecyclerViewAdapterTheLoai adapter;
    ArrayList<TheLoai> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);

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
        findViewById(R.id.img_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        db = new SQLiteDB(this);
        list = db.getAllTheLoai();
        adapter = new RecyclerViewAdapterTheLoai(this,list);

        click(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.top_app_bars,menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = (SearchView) menu.getItem(R.id.search_top_app_bars).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        return true;
//    }

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
                        Intent intent2 = new Intent(context, PhieuMuonActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.the_loai_activity:
                        drawerLayout.closeDrawer(GravityCompat.START);
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