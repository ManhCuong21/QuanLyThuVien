package com.example.duanmau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.Database.SQLiteDB;
import com.example.duanmau.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoanhThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoanhThuFragment extends Fragment {

    public DoanhThuFragment() {
    }

    public static DoanhThuFragment newInstance(String param1, String param2) {
        DoanhThuFragment fragment = new DoanhThuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    EditText edtNgayDauDoanhThu,edtNgayCuoiDoanhThu;
    ImageView calendarNgayDauDoanhThu,calendarNgayCuoiDoanhThu;
    TextView tvDoanhThu;
    SQLiteDB db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        calendarNgayDauDoanhThu = view.findViewById(R.id.calendarNgayDauDoanhThu);
        calendarNgayCuoiDoanhThu = view.findViewById(R.id.calendarNgayCuoiDoanhThu);
        edtNgayDauDoanhThu = view.findViewById(R.id.edtNgayDauDoanhThu);
        edtNgayCuoiDoanhThu = view.findViewById(R.id.edtNgayCuoiDoanhThu);
        showCalendar(calendarNgayDauDoanhThu,edtNgayDauDoanhThu);
        showCalendar(calendarNgayCuoiDoanhThu,edtNgayCuoiDoanhThu);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);

        db = new SQLiteDB(getContext());
        view.findViewById(R.id.btnDoanhThu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edtNgayDau = edtNgayDauDoanhThu.getText().toString();
                String edtNgayCuoi = edtNgayCuoiDoanhThu.getText().toString();
                if (edtNgayDau.length() == 0 || edtNgayCuoi.length() == 0){
                    Toast.makeText(getContext(), "Bạn phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    tvDoanhThu.setText("");
                }else {
                    String ngayDau = formatDate(edtNgayDau);
                    String ngayCuoi = formatDate(edtNgayCuoi);
                    int tongTien = db.getDoanhThu(ngayDau,ngayCuoi);
                    tvDoanhThu.setText("Tổng tiền: "+tongTien+" VNĐ");
                }
            }
        });
        return view;
    }

    private String formatDate(String time){
        String day = time.substring(0,2);
        String month = time.substring(3,5);
        String year = time.substring(6);
        String date = year+"/"+month+"/"+day;
        return date;
    }

    private void showCalendar(ImageView img,EditText edt){
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
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