package com.example.duanmau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ThemMoiThanhVienActivity extends AppCompatActivity {

    EditText edtThemMoiTenThanhVien,edtThemMoiNamSinh;
    SQLiteDB db;
    ImageView calendarNamSinhThanhVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi_thanh_vien);

        db = new SQLiteDB(this);

        edtThemMoiTenThanhVien = findViewById(R.id.edtThemMoiTenThanhVien);
        edtThemMoiNamSinh = findViewById(R.id.edtThemMoiNamSinh);
        calendarNamSinhThanhVien = findViewById(R.id.calendarNamSinhThanhVien);
        showCalendar(calendarNamSinhThanhVien,edtThemMoiNamSinh);

        findViewById(R.id.btnThemMoiThanhVien).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hoTen = edtThemMoiTenThanhVien.getText().toString();
                String namSinh = edtThemMoiNamSinh.getText().toString();

                if (hoTen.length() == 0 || namSinh.length() == 0){
                    Toast.makeText(ThemMoiThanhVienActivity.this, "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<ThanhVien> list = db.getAllThanhVien();
                for (int i = 0; i < list.size();i++){
                    ThanhVien thanhVien = list.get(i);
                    if (thanhVien.getHoTen().equalsIgnoreCase(hoTen)){
                        Toast.makeText(ThemMoiThanhVienActivity.this, "Tên thành viên đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                    ThanhVien thanhVien = new ThanhVien();
                thanhVien.setHoTen(hoTen);
                thanhVien.setNamSinh(namSinh);
                long result = db.insertThanhVien(thanhVien);
                if (result > 0){
                    Toast.makeText(ThemMoiThanhVienActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemMoiThanhVienActivity.this,ThanhVienActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ThemMoiThanhVienActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btnReturnThanhVien).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMoiThanhVienActivity.this,ThanhVienActivity.class);
                startActivity(intent);
            }
        });

    }

    private void showCalendar(ImageView img,EditText edt){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(ThemMoiThanhVienActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String date = "";
                                if (dayOfMonth < 10){
                                    date = "0"+dayOfMonth+"/"+month+"/"+year;
                                }else {
                                    date = dayOfMonth+"/"+month+"/"+year;
                                }

                                edt.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });
    }
}