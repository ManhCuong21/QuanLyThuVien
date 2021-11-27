package com.example.duanmau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ThemMoiPhieuMuonActivity extends AppCompatActivity {

    AutoCompleteTextView edtThanhVienPhieuMuon,edtSachPhieuMuon;
    EditText edtGiaThuePhieuMuon,edtNgayThuePhieuMuon;
    ArrayList<ThanhVien> list_thanh_vien;
    ArrayList<Sach> list_sach;
    ArrayList<String> list_thanh_vien_2,list_sach_2;
    SQLiteDB db;
    int maTv = 0,id_sach = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi_phieu_muon);

        edtThanhVienPhieuMuon = findViewById(R.id.edtThanhVienPhieuMuon);
        edtSachPhieuMuon = findViewById(R.id.edtSachPhieuMuon);
        edtGiaThuePhieuMuon = findViewById(R.id.edtGiaThuePhieuMuon);
        edtNgayThuePhieuMuon = findViewById(R.id.edtNgayThuePhieuMuon);
        calendar();

        db = new SQLiteDB(this);
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



        edtThanhVienPhieuMuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThanhVien thanhVien = list_thanh_vien.get(position);
                maTv = thanhVien.getMaTv();
            }
        });

        edtSachPhieuMuon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Sach sach = list_sach.get(position);
                    edtGiaThuePhieuMuon.setText(String.valueOf(sach.getGiaThue()));
                    id_sach = sach.getId();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("sharePrefer",MODE_PRIVATE);
        String tenDangNhap = sharedPreferences.getString("tenDangNhap",null);


        findViewById(R.id.btnThemMoiPhieuMuon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraNhap() == false){
                    return;
                }
                int giaThue = Integer.parseInt(edtGiaThuePhieuMuon.getText().toString());
                String ngayThue = edtNgayThuePhieuMuon.getText().toString();
                String day = ngayThue.substring(0,2);
                String month = ngayThue.substring(3,5);
                String year = ngayThue.substring(6);
                String date = year+"/"+month+"/"+day;

                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setMaTt(tenDangNhap);
                phieuMuon.setMaTv(maTv);
                phieuMuon.setIdSach(id_sach);
                phieuMuon.setGiaThue(giaThue);
                phieuMuon.setNgayThue(date);
                phieuMuon.setTraSach(0);

                long result = db.insertPhieuMuon(phieuMuon);
                if (result>0){
                    Toast.makeText(ThemMoiPhieuMuonActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemMoiPhieuMuonActivity.this,PhieuMuonActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ThemMoiPhieuMuonActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnReturnPhieuMuon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMoiPhieuMuonActivity.this,PhieuMuonActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.row_list_item,list_thanh_vien_2);
        edtThanhVienPhieuMuon.setAdapter(adapter1);
        ArrayAdapter adapter2 = new ArrayAdapter(this,R.layout.row_list_item,list_sach_2);
        edtSachPhieuMuon.setAdapter(adapter2);

    }

    private boolean kiemTraNhap(){
        if (edtThanhVienPhieuMuon.getText().toString().length() == 0 ||
            edtSachPhieuMuon.getText().toString().length() == 0 ||
            edtGiaThuePhieuMuon.getText().toString().length() == 0 ||
            edtNgayThuePhieuMuon.getText().toString().length() == 0 ){
            Toast.makeText(this, "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void calendar(){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtNgayThuePhieuMuon.setText(simpleDateFormat.format(calendar.getTime()));
    }
}