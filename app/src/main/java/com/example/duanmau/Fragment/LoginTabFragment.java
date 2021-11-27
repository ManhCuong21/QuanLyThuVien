package com.example.duanmau.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.Activity.MainActivity;
import com.example.duanmau.R;

public class LoginTabFragment extends Fragment {

    EditText edtTenDangNhap,edtMatKhau;
    TextView tvQuenMatKhau;
    Button btnDangNhap,btnHuyDangNhap;
    ToggleButton btnCheckPass;
    CheckBox ckbGhiNho;
    float v = 0;
    SharedPreferences sharedPreferences;

    public LoginTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        sharedPreferences = getContext().getSharedPreferences("sharePrefer", Context.MODE_PRIVATE);


        edtTenDangNhap = root.findViewById(R.id.edtTenDangNhap);
        edtMatKhau = root.findViewById(R.id.edtMatKhau);
        tvQuenMatKhau = root.findViewById(R.id.tvQuenMatKhau);
        btnDangNhap = root.findViewById(R.id.btnDangNhap);
        btnCheckPass = root.findViewById(R.id.btnCheckPass);
        btnHuyDangNhap = root.findViewById(R.id.btnHuyDangNhap);
        ckbGhiNho = root.findViewById(R.id.ckbGhiNho);

        edtTenDangNhap.setTranslationX(800);
        edtMatKhau.setTranslationX(800);
        tvQuenMatKhau.setTranslationX(800);
        btnCheckPass.setTranslationX(800);
        btnDangNhap.setTranslationX(800);
        btnHuyDangNhap.setTranslationX(800);
        ckbGhiNho.setTranslationX(800);

        edtTenDangNhap.setAlpha(v);
        edtMatKhau.setAlpha(v);
        tvQuenMatKhau.setAlpha(v);
        btnCheckPass.setAlpha(v);
        btnDangNhap.setAlpha(v);
        btnHuyDangNhap.setAlpha(v);
        ckbGhiNho.setAlpha(v);

        edtTenDangNhap.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edtMatKhau.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tvQuenMatKhau.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnCheckPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnDangNhap.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        btnHuyDangNhap.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        ckbGhiNho.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        btnCheckPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCheckPass.isChecked()){
                    edtMatKhau.setTransformationMethod(null);
                }else {
                    edtMatKhau.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMatKhau.getText().length() == 0 || edtTenDangNhap.getText().length()==0){
                    Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDB db = new SQLiteDB(getContext());
                String tenDangNhap = edtTenDangNhap.getText().toString();
                String matKhau = edtMatKhau.getText().toString();

                ThuThu nguoiDung = new ThuThu();
                nguoiDung.setMaTt(tenDangNhap);
                nguoiDung.setPassword(matKhau);

                boolean kt = db.kiemTra(nguoiDung);
                String tenNguoiDung = db.getTenThuThu(nguoiDung);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(kt == true){
                    editor.putString("tenNguoiDung",tenNguoiDung);
                    editor.putString("tenDangNhap",tenDangNhap);
                    editor.putString("matKhau",matKhau);
                    editor.putBoolean("ghiNho",ckbGhiNho.isChecked());
                    editor.apply();
                    Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHuyDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTenDangNhap.setText("");
                edtMatKhau.setText("");
            }
        });
        ghiNhoDangNhap();
        return root;
    }

    private void ghiNhoDangNhap(){
            String tenDangNhap = sharedPreferences.getString("tenDangNhap","");
            String matKhau = sharedPreferences.getString("matKhau","");
            boolean ckbGhiNho1 = sharedPreferences.getBoolean("ghiNho",false);
            if (ckbGhiNho1){
                edtTenDangNhap.setText(tenDangNhap);
                edtMatKhau.setText(matKhau);
                ckbGhiNho.setChecked(ckbGhiNho1);
            }

    }

}
