package com.example.duanmau.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.duanmau.Model.PhieuMuon;
import com.example.duanmau.Model.Sach;
import com.example.duanmau.Model.ThanhVien;
import com.example.duanmau.Model.TheLoai;
import com.example.duanmau.Model.ThongKe;
import com.example.duanmau.Model.ThuThu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQLiteDB extends SQLiteOpenHelper {
    public SQLiteDB(@Nullable Context context) {
        super(context, "sach.db", null, 1);
    }


    public static final String TABLE_THU_THU = "THUTHU";
    public static final String TEN_THU_THU = "TEN_THU_THU";
    public static final String MATT = "MATT";
    public static final String PASSWORD = "PASSWORD";

    public static final String TABLE_THANH_VIEN = "THANHVIEN";
    public static final String TEN_THANH_VIEN = "TENTHANHVIEN";
    public static final String MATV = "MATV";
    public static final String NAMSINH = "NAMSINH";

    public static final String TABLE_THE_LOAI = "TABLE_THE_LOAI";
    public static final String ID_THE_LOAI = "ID_THE_LOAI";
    public static final String TENTHELOAI = "TENTHELOAI";

    public static final String TABLE_SACH = "TABLE_SACH";
    public static final String ID_SACH = "ID_SACH";
    public static final String TENSACH = "TENSACH";
    public static final String GIATHUE = "GIATHUE";

    public static final String TABLE_PHIEU_MUON = "TABLE_PHIEU_MUON";
    public static final String ID_PHIEU_MUON = "ID_PHIEU_MUON";
    public static final String NGAYTHUE = "NGAYTHUE";
    public static final String TRASACH = "TRASACH";

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_THU_THU = "CREATE TABLE "+TABLE_THU_THU+"(" +
                MATT+" TEXT PRIMARY KEY,"+
                TEN_THU_THU+" TEXT NOT NULL,"+
                PASSWORD+" TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_THU_THU);

        String CREATE_TABLE_THANH_VIEN = "CREATE TABLE "+TABLE_THANH_VIEN+"(" +
                MATV+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TEN_THANH_VIEN+" TEXT NOT NULL,"+
                NAMSINH+" TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE_THANH_VIEN);

        String CREATE_TABLE_THE_LOAI = "CREATE TABLE "+TABLE_THE_LOAI+"("+
                ID_THE_LOAI+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TENTHELOAI+" NVARCHAR)";
        db.execSQL(CREATE_TABLE_THE_LOAI);

        String CREATE_TABLE_SACH = "CREATE TABLE "+TABLE_SACH+"("+
                ID_SACH+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TENSACH+" NVARCHAR,"+
                GIATHUE+" INTEGER,"+
                ID_THE_LOAI+" INTEGER REFERENCES "+TABLE_THE_LOAI+ "("+ID_THE_LOAI+"))";
        db.execSQL(CREATE_TABLE_SACH);

        String CREATE_TABLE_PHIEU_MUON = "CREATE TABLE "+TABLE_PHIEU_MUON+"("+
                ID_PHIEU_MUON+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                MATT+" TEXT REFERENCES "+TABLE_THU_THU+"("+MATT+"),"+
                MATV+" INTEGER REFERENCES "+TABLE_THANH_VIEN+"("+MATV+"),"+
                ID_SACH+" INTEGER REFERENCES "+TABLE_SACH+"("+ID_SACH+"),"+
                GIATHUE+" INTEGER NOT NULL,"+
                NGAYTHUE+" DATE NOT NULL,"+
                TRASACH+" INTEGER NOT NULL)";
        db.execSQL(CREATE_TABLE_PHIEU_MUON);
    }

    public long insertPhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MATT,phieuMuon.getMaTt());
        values.put(MATV,phieuMuon.getMaTv());
        values.put(ID_SACH,phieuMuon.getIdSach());
        values.put(GIATHUE,phieuMuon.getGiaThue());
        values.put(NGAYTHUE,phieuMuon.getNgayThue());
        values.put(TRASACH,phieuMuon.getTraSach());
        long result = db.insert(TABLE_PHIEU_MUON,null,values);
        return result;
    }

    public int deletePhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_PHIEU_MUON,ID_PHIEU_MUON+"=?",new String[]{String.valueOf(phieuMuon.getId())});
        return result;
    }

    public int updatePhieuMuon(PhieuMuon phieuMuon){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MATV,phieuMuon.getMaTv());
        values.put(ID_SACH,phieuMuon.getIdSach());
        values.put(GIATHUE,phieuMuon.getGiaThue());
        values.put(NGAYTHUE,phieuMuon.getNgayThue());
        values.put(TRASACH,phieuMuon.getTraSach());
        int result = db.update(TABLE_PHIEU_MUON,values,ID_PHIEU_MUON+"=?",new String[]{String.valueOf(phieuMuon.getId())});
        return result;
    }

    public ArrayList<PhieuMuon> getAllPhieuMuon(){
        String SELECT_PHIEU_MUON = "SELECT * FROM "+TABLE_PHIEU_MUON+
                " JOIN "+TABLE_THU_THU+" ON THUTHU.MATT = TABLE_PHIEU_MUON.MATT"+
                " JOIN "+TABLE_THANH_VIEN+" ON THANHVIEN.MATV = TABLE_PHIEU_MUON.MATV"+
                " JOIN "+TABLE_SACH+" ON TABLE_SACH.ID_SACH = TABLE_PHIEU_MUON.ID_SACH";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor_phieu_muon = db.rawQuery(SELECT_PHIEU_MUON,null);
        ArrayList<PhieuMuon> list = new ArrayList<>();
        if (cursor_phieu_muon.getCount()>0){
            cursor_phieu_muon.moveToFirst();
            while (!cursor_phieu_muon.isAfterLast()){
                int id = cursor_phieu_muon.getInt(cursor_phieu_muon.getColumnIndex(ID_PHIEU_MUON));
                String maTt = cursor_phieu_muon.getString(cursor_phieu_muon.getColumnIndex(MATT));
                String tenThuThu = cursor_phieu_muon.getString(cursor_phieu_muon.getColumnIndex(TEN_THU_THU));
                int maTv = cursor_phieu_muon.getInt(cursor_phieu_muon.getColumnIndex(MATV));
                String tenThanhVien = cursor_phieu_muon.getString(cursor_phieu_muon.getColumnIndex(TEN_THANH_VIEN));
                int id_sach = cursor_phieu_muon.getInt(cursor_phieu_muon.getColumnIndex(ID_SACH));
                String tenSach = cursor_phieu_muon.getString(cursor_phieu_muon.getColumnIndex(TENSACH));
                int giaThue = cursor_phieu_muon.getInt(cursor_phieu_muon.getColumnIndex(GIATHUE));
                String ngayThue = cursor_phieu_muon.getString(cursor_phieu_muon.getColumnIndex(NGAYTHUE));
                int traSach = cursor_phieu_muon.getInt(cursor_phieu_muon.getColumnIndex(TRASACH));

                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.setId(id);
                phieuMuon.setMaTt(maTt);
                phieuMuon.setTenThuThu(tenThuThu);
                phieuMuon.setMaTv(maTv);
                phieuMuon.setTenThanhVien(tenThanhVien);
                phieuMuon.setIdSach(id_sach);
                phieuMuon.setTenSach(tenSach);
                phieuMuon.setGiaThue(giaThue);
                phieuMuon.setNgayThue(ngayThue);
                phieuMuon.setTraSach(traSach);
                list.add(phieuMuon);

                cursor_phieu_muon.moveToNext();
            }
            cursor_phieu_muon.close();
        }
        return list;
    }

    public long insertTheLoai(TheLoai theLoai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TENTHELOAI,theLoai.getTenTheLoai());
        long result = db.insert(TABLE_THE_LOAI,null,values);
        return result;
    }

    public int updateTheLoai(TheLoai theLoai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TENTHELOAI,theLoai.getTenTheLoai());
        int result = db.update(TABLE_THE_LOAI,values,ID_THE_LOAI +"=?",new String[]{String.valueOf(theLoai.getId())});
        return result;
    }

    public int deleteTheLoai(TheLoai theLoai){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_THE_LOAI,ID_THE_LOAI +" =?",new String[]{String.valueOf(theLoai.getId())} );
        return result;
    }

    public ArrayList<TheLoai> getAllTheLoai(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<TheLoai> list = new ArrayList<>();
        String SELECT = "SELECT * FROM "+TABLE_THE_LOAI;
        Cursor cursor = db.rawQuery(SELECT,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(ID_THE_LOAI));
                String tenTheLoai = cursor.getString(cursor.getColumnIndex(TENTHELOAI));
                TheLoai theLoai = new TheLoai(id,tenTheLoai);
                list.add(theLoai);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public long insertSach(Sach sach){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TENSACH,sach.getTenSach());
        values.put(GIATHUE,sach.getGiaThue());
        values.put(ID_THE_LOAI,sach.getIdTheLoai());
        long result = db.insert(TABLE_SACH,null,values);
        return result;
    }

    public int updateSach(Sach sach){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TENSACH,sach.getTenSach());
        values.put(GIATHUE,sach.getGiaThue());
        values.put(ID_THE_LOAI,sach.getIdTheLoai());
        int result = db.update(TABLE_SACH,values,ID_SACH + "=?",new String[]{String.valueOf(sach.getId())});
        return result;
    }

    public int deleteSach(Sach sach){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_SACH,ID_SACH + "=?",new String[]{String.valueOf(sach.getId())});
        return result;
    }

    public ArrayList<Sach> getAllSach(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Sach> list = new ArrayList<>();
        String SELECT = "SELECT * FROM "+TABLE_SACH+
                        " JOIN "+TABLE_THE_LOAI+" ON TABLE_THE_LOAI.ID_THE_LOAI = TABLE_SACH.ID_THE_LOAI";
        Cursor cursor = db.rawQuery(SELECT,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(ID_SACH));
                String tenSach = cursor.getString(cursor.getColumnIndex(TENSACH));
                int giaThue = cursor.getInt(cursor.getColumnIndex(GIATHUE));
                String theLoai = cursor.getString(cursor.getColumnIndex(TENTHELOAI));
                Sach sach = new Sach();
                sach.setId(id);
                sach.setTenSach(tenSach);
                sach.setGiaThue(giaThue);
                sach.setTheLoai(theLoai);
                list.add(sach);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public long insertThuThu(ThuThu nguoiDung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_THU_THU,nguoiDung.getName());
        values.put(MATT,nguoiDung.getMaTt());
        values.put(PASSWORD,nguoiDung.getPassword());
        long result = db.insert(TABLE_THU_THU,null,values);
        return result;
    }

    public int updateThuThu(ThuThu thuThu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_THU_THU,thuThu.getName());
        values.put(MATT,thuThu.getMaTt());
        values.put(PASSWORD,thuThu.getPassword());
        int result = db.update(TABLE_THU_THU,values,MATT+"=?",new String[]{thuThu.getMaTt()});
        return result;
    }

    public int deleteThuThu(ThuThu thuThu){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_THU_THU,MATT+"=?",new String[]{thuThu.getMaTt()});
        return result;
    }

    public ArrayList<ThuThu> getAllThuThu(){
        ArrayList<ThuThu> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT = "SELECT * FROM "+TABLE_THU_THU;
        Cursor cursor = db.rawQuery(SELECT,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String tenNguoiDung = cursor.getString(cursor.getColumnIndex(TEN_THU_THU));
                String matt = cursor.getString(cursor.getColumnIndex(MATT));
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));

                ThuThu nguoiDung = new ThuThu();
                nguoiDung.setName(tenNguoiDung);
                nguoiDung.setMaTt(matt);
                nguoiDung.setPassword(password);
                list.add(nguoiDung);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }


    public String getTenThuThu(ThuThu nguoiDung){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+TEN_THU_THU+" FROM "+TABLE_THU_THU+" WHERE "+MATT+" = ? AND "+PASSWORD+" = ?",new String[]{nguoiDung.getMaTt(),nguoiDung.getPassword()});
        String tenNguoiDung = "";
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                tenNguoiDung = cursor.getString(cursor.getColumnIndex(TEN_THU_THU));
                cursor.moveToNext();
            }
            cursor.close();
        }
        return tenNguoiDung;
    }

    public boolean kiemTra(ThuThu nguoiDung){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_THU_THU+" WHERE "+MATT+" = ? AND "+PASSWORD+" = ?",new String[]{nguoiDung.getMaTt(),nguoiDung.getPassword()});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public long insertThanhVien(ThanhVien thanhVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_THANH_VIEN,thanhVien.getHoTen());
        values.put(NAMSINH,thanhVien.getNamSinh());
        long result = db.insert(TABLE_THANH_VIEN,null,values);
        return result;
    }

    public ArrayList<ThanhVien> getAllThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT = "SELECT * FROM "+TABLE_THANH_VIEN;
        Cursor cursor = db.rawQuery(SELECT,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                int maTv = cursor.getInt(cursor.getColumnIndex(MATV));
                String hoTen = cursor.getString(cursor.getColumnIndex(TEN_THANH_VIEN));
                String namSinh = cursor.getString(cursor.getColumnIndex(NAMSINH));

                ThanhVien thanhVien = new ThanhVien();
                thanhVien.setMaTv(maTv);
                thanhVien.setHoTen(hoTen);
                thanhVien.setNamSinh(namSinh);
                list.add(thanhVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public int upDateThanhVien(ThanhVien thanhVien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN_THANH_VIEN,thanhVien.getHoTen());
        values.put(NAMSINH,thanhVien.getNamSinh());
        int result = db.update(TABLE_THANH_VIEN,values,MATV +"=?",new String[]{String.valueOf(thanhVien.getMaTv())});
        return result;
    }

    public int deleteThanhVien(ThanhVien thanhVien){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_THANH_VIEN,MATV +" =?",new String[]{String.valueOf(thanhVien.getMaTv())});
        return result;
    }

    public ArrayList<ThongKe> getTop10(){
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT = "SELECT TENSACH,count(TABLE_PHIEU_MUON.ID_SACH) as SOLANMUON FROM TABLE_PHIEU_MUON"+
                " JOIN TABLE_SACH ON TABLE_PHIEU_MUON.ID_SACH = TABLE_SACH.ID_SACH"+
                " GROUP BY TENSACH ORDER BY SOLANMUON DESC LIMIT 10";
        Cursor cursor = db.rawQuery(SELECT,null);
        ArrayList<ThongKe> list = new ArrayList<>();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String tenSach = cursor.getString(cursor.getColumnIndex(TENSACH));
                String soLanMuon = cursor.getString(cursor.getColumnIndex("SOLANMUON"));

                ThongKe thongKe = new ThongKe();
                thongKe.setTenSach(tenSach);
                thongKe.setSoLanMuon(soLanMuon);

                list.add(thongKe);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public int getDoanhThu(String ngayDau,String ngayCuoi){
        String SELECT = "SELECT SUM(GIATHUE) AS TONGTIEN FROM TABLE_PHIEU_MUON"+
        " WHERE NGAYTHUE BETWEEN '"+ngayDau+"' AND '"+ngayCuoi+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT,null);
        int tongTien = 0;
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            tongTien = cursor.getInt(cursor.getColumnIndex("TONGTIEN"));
            cursor.close();
        }
        return tongTien;
    }

    public ArrayList<ThuThu> getThuThuSearch(String tenDangNhap){
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT = "SELECT * FROM "+ TABLE_THU_THU +" WHERE "+ TABLE_THU_THU+"."+MATT +" LIKE '"+tenDangNhap+"%'";
        Cursor cursor = db.rawQuery(SELECT,null);
        ArrayList<ThuThu> list = new ArrayList<>();
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String tenNguoiDung = cursor.getString(cursor.getColumnIndex(TEN_THU_THU));
                String matt = cursor.getString(cursor.getColumnIndex(MATT));
                String password = cursor.getString(cursor.getColumnIndex(PASSWORD));

                ThuThu nguoiDung = new ThuThu();
                nguoiDung.setName(tenNguoiDung);
                nguoiDung.setMaTt(matt);
                nguoiDung.setPassword(password);
                list.add(nguoiDung);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
