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
                    Toast.makeText(getContext(), "????ng k?? th??nh c??ng", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "????ng k?? th???t b???i", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return root;
    }

    private boolean kiemTraNhap(){
        ArrayList<ThuThu> list = db.getAllThuThu();

        String tenNguoiDung = edtTenNguoiDung.getText().toString();


        if(tenNguoiDung.length() == 0){
            Toast.makeText(getContext(), "Ch??a nh???p t??n ng?????i d??ng", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tenNguoiDung.length() < 5 || tenNguoiDung.length() > 15){
            Toast.makeText(getContext(), "Nh???p t??n ng?????i d??ng c?? ????? d??i 5 - 15 k?? t???", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (tenNguoiDung.substring(0,1).equals(tenNguoiDung.substring(0,1).toUpperCase()) == false){
            Toast.makeText(getContext(), "Ch??? c??i ?????u ti??n ph???i l?? ch??? in hoa", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(edtTenDangNhap.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Ch??a nh???p t??n ????ng nh???p", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtMatKhau.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Ch??a nh???p m???t kh???u", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(edtNhapLaiMatKhau.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Ch??a nh???p l???i m???t kh???u", Toast.LENGTH_SHORT).show();
            return false;
        }

        String password1 = edtMatKhau.getText().toString();
        String enterPass = edtNhapLaiMatKhau.getText().toString();

        if(password1.compareTo(enterPass) != 0){
            Toast.makeText(getContext(), "Password kh??ng ?????ng nh???t", Toast.LENGTH_SHORT).show();
            return false;
        }
        for(int i = 0; i < list.size();i++){
            String username = list.get(i).getMaTt();
            if (edtTenDangNhap.getText().toString().equals(username)){
                Toast.makeText(getContext(), "T??n ????ng nh???p ???? t???n t???i", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
