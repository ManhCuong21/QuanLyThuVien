package com.example.duanmau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.R;
import com.example.duanmau.Database.SQLiteDB;

import java.util.ArrayList;

public class ThemMoiTheLoaiActivity extends AppCompatActivity {

    ImageView btnReturnTheLoai;
    EditText edtThemMoiTenTheLoai;
    Button btnThemMoiTheLoai;
    SQLiteDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_moi_the_loai);

        db = new SQLiteDB(this);

        edtThemMoiTenTheLoai = findViewById(R.id.edtThemMoiTenTheLoai);
        btnThemMoiTheLoai = findViewById(R.id.btnThemMoiTheLoai);

        btnThemMoiTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtThemMoiTenTheLoai.getText().toString().length() == 0){
                    Toast.makeText(ThemMoiTheLoaiActivity.this, "Tên thể loại không được trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<TheLoai> list = db.getAllTheLoai();
                for (int i = 0; i < list.size();i++){
                    TheLoai theLoai = list.get(i);
                    if (theLoai.getTenTheLoai().equalsIgnoreCase(edtThemMoiTenTheLoai.getText().toString())){
                        Toast.makeText(ThemMoiTheLoaiActivity.this, "Tên thể loại đã tồn tại", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                String tenTheLoai = edtThemMoiTenTheLoai.getText().toString();
                TheLoai theLoai = new TheLoai();
                theLoai.setTenTheLoai(tenTheLoai);
                long result = db.insertTheLoai(theLoai);
                if (result>0){
                    Toast.makeText(ThemMoiTheLoaiActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemMoiTheLoaiActivity.this, TheLoaiActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(ThemMoiTheLoaiActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnReturnTheLoai = findViewById(R.id.btnReturnTheLoai);

        btnReturnTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMoiTheLoaiActivity.this, TheLoaiActivity.class);
                startActivity(intent);
            }
        });

    }

}