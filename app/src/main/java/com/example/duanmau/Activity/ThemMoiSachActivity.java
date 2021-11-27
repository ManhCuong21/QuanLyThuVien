package com.example.duanmau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.Model.Sach;
import com.example.duanmau.R;
import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.Database.SQLiteDB;

import java.util.ArrayList;

public class ThemMoiSachActivity extends AppCompatActivity {

    SQLiteDB db;
    AutoCompleteTextView edtTheLoaiSach;
    EditText edtThemMoiTenSach,edtThemMoiGiaThueSach;
    ArrayList<String> list_the_loai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi_sach);

        db = new SQLiteDB(this);

        edtThemMoiTenSach = findViewById(R.id.edtThemMoiTenSach);
        edtThemMoiGiaThueSach = findViewById(R.id.edtThemMoiGiaThueSach);

        list_the_loai = new ArrayList<>();
        ArrayList<TheLoai> list = db.getAllTheLoai();

        for(int i =0;i < list.size();i++){
            TheLoai theLoai = list.get(i);
            String tenTheLoai = theLoai.getTenTheLoai();
            list_the_loai.add(tenTheLoai);
        }



        findViewById(R.id.btnThemMoiSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtThemMoiTenSach.getText().toString().length() == 0 ||
                        edtThemMoiGiaThueSach.getText().toString().length() == 0 ||
                        edtTheLoaiSach.getText().toString().length()==0){
                    Toast.makeText(ThemMoiSachActivity.this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }


                String tenSach = edtThemMoiTenSach.getText().toString();
                int giaThue = Integer.parseInt(edtThemMoiGiaThueSach.getText().toString());
                String theLoai = edtTheLoaiSach.getText().toString();


                int id_the_loai = 0;
                for(int i = 0; i < list.size();i++){
                    String tenTheLoai = list.get(i).getTenTheLoai();
                    if (theLoai.equals(tenTheLoai)){
                        id_the_loai = list.get(i).getId();
                    }
                }

                Sach sach = new Sach();
                sach.setTenSach(tenSach);
                sach.setGiaThue(giaThue);
                sach.setIdTheLoai(id_the_loai);
                long result = db.insertSach(sach);
                if (result > 0 ){
                    Toast.makeText(ThemMoiSachActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemMoiSachActivity.this, SachActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ThemMoiSachActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });




        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.row_list_item,list_the_loai);
        edtTheLoaiSach = findViewById(R.id.edtTheLoaiSach);
        edtTheLoaiSach.setAdapter(adapter);

        findViewById(R.id.btnReturnSach).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMoiSachActivity.this,SachActivity.class);
                startActivity(intent);
            }
        });
    }
}