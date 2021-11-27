package com.example.duanmau.Fragment;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.Model.ThuThu;
import com.example.duanmau.R;

import java.util.ArrayList;

public class SignupTabFragment extends Fragment {

    EditText edtTenNguoiDung,edtTenDangNhap,edtMatKhau,edtNhapLaiMatKhau;
    Button btnDangKy;
    ToggleButton btnCheckPass,btnCheckPass2;
    SQLiteDB db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        db = new SQLiteDB(getContext());

        edtTenNguoiDung = root.findViewById(R.id.edtTenNguoiDung);
        edtTenDangNhap = root.findViewById(R.id.edtTenDangNhap);
        edtMatKhau = root.findViewById(R.id.edtMatKhau);
        edtNhapLaiMatKhau = root.findViewById(R.id.edtNhapLaiMatKhau);
        btnDangKy = root.findViewById(R.id.btnDangKy);
        btnCheckPass = root.findViewById(R.id.btnCheckPass);
        btnCheckPass2 = root.findViewById(R.id.btnCheckPass2);


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

        btnCheckPass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnCheckPass2.isChecked()){
                    edtNhapLaiMatKhau.setTransformationMethod(null);
                }else {
                    edtNhapLaiMatKhau.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kiemTraNhap() == false){
                    return;
                }
                String tenNguoiDung = edtTenNguoiDung.getText().toString();
                String tenDangNhap = edtTenDangNhap.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                ThuThu nguoiDung = new ThuThu();
                nguoiDung.setName(tenNguoiDung);
                nguoiDung.setMaTt(tenDangNhap);
                nguoiDung.setPassword(matKhau);

                long result = db.insertThuThu(nguoiDung);
                if (result > 0){
                    Toast.makeText(getContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    private boolean kiemTraNhap(){
        ArrayList<ThuThu> list = db.getAllThuThu();

        String tenNguoiDung = edtTenNguoiDung.getText().toString();


        if(tenNguoiDung.length() == 0){
            Toast.makeText(getContext(), "Chưa nhập tên người dùng", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tenNguoiDung.length() < 5 || tenNguoiDung.length() > 15){
            Toast.makeText(getContext(), "Nhập tên người dùng có độ dài 5 - 15 kí tự", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tenNguoiDung.substring(0,1).equals(tenNguoiDung.substring(0,1).toUpperCase()) == false){
            Toast.makeText(getContext(), "Chữ cái đầu tiên phải là chữ in hoa", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(edtTenDangNhap.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Chưa nhập tên đăng nhập", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtMatKhau.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtNhapLaiMatKhau.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Chưa nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            return false;
        }

        String password1 = edtMatKhau.getText().toString();
        String enterPass = edtNhapLaiMatKhau.getText().toString();

        if(password1.compareTo(enterPass) != 0){
            Toast.makeText(getContext(), "Password không đồng nhất", Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i = 0; i < list.size();i++){
            String username = list.get(i).getMaTt();
            if (edtTenDangNhap.getText().toString().equals(username)){
                Toast.makeText(getContext(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
