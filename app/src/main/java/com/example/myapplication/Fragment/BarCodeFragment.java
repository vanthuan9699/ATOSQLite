package com.example.myapplication.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Database.Database;
import com.example.myapplication.R;

import java.util.ArrayList;

public class BarCodeFragment extends Fragment {
    View view;
    EditText edt_soluong, edt_trangthai, edt_ghichu, edt_soct, edtNgay, edtMavt, edtMavitri, edtMalo, edtKhohang, edtPhieuPO, edtPhieuxh, edtPhieuth;
    Spinner sp_mact, sp_magd;
    Button btn_save;

    public Database database;
    ArrayAdapter adapter, adapter1;
    ArrayList<String> arrayList, arrayList1;


    public BarCodeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_barcode, container, false);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter.notifyDataSetChanged();

        arrayList1 = new ArrayList<>();
        adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter1.notifyDataSetChanged();


        database = new Database(getContext(), "QuanLy.sqlite", null, 1);
        database.QuerryData("create table if not exists zc_barcode_auto(Ma_ct char(3), Ma_gd char(2), So_ct char(12), Ngay_ct smalldatetime, Ma_vt char(16), Ma_vi_tri char(16), Ma_lo char(16), Ma_kho char(16), So_luong numeric(16,2), Stt_rec_po char(13), Stt_rec_dn char(13), Stt_rec_th char(13), Status char(1), Status_fast char(1))");
        init();
        addTuDong();
        addGiaoDich();
        Save();
        return view;
    }
    private void addTuDong() {
        Cursor cursor = database.GetData("SELECT Ma_ct FROM zcdmct_tudong ");
        while (cursor.moveToNext()){
            String mact = cursor.getString(0);
            arrayList.add(mact);
            sp_mact.setAdapter(adapter);
        }
    }
    private void addGiaoDich() {
        Cursor cursor = database.GetData("SELECT Ma_ct FROM zcdmct_giaodich ");
        while (cursor.moveToNext()){
            String magd = cursor.getString(0);
            arrayList1.add(magd);
            sp_magd.setAdapter(adapter1);
        }
    }
    private void Save() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma_ct = sp_mact.getSelectedItem().toString();
                String ma_gd = sp_magd.getSelectedItem().toString();
                String so_ct = edt_soct.getText().toString();
                String ngay = edtNgay.getText().toString();
                String mavatu = edtMavt.getText().toString();
                String mavitri = edtMavitri.getText().toString();
                String malo = edtMalo.getText().toString();
                String makho = edtKhohang.getText().toString();
                String soluong = edt_soluong.getText().toString();
                String po = edtPhieuPO.getText().toString();
                String dn = edtPhieuxh.getText().toString();
                String th = edtPhieuth.getText().toString();
                String trangthai = edt_trangthai.getText().toString();
                String status = edt_ghichu.getText().toString();

                String sql = "INSERT INTO zc_barcode_auto (Ma_ct, " +
                        "Ma_gd, So_ct, Ngay_ct, Ma_vt, Ma_vi_tri, " +
                        "Ma_lo, Ma_kho, So_luong, Stt_rec_po, " +
                        "Stt_rec_dn, Stt_rec_th,Status, Status_fast)" +
                        " VALUES ('" + ma_ct + "', " +
                        "'" + ma_gd + "', " +
                        "'" + so_ct + "', " +
                        "'" + ngay + "', " +
                        "'" + mavatu + "', " +
                        "'" + mavitri + "'," +
                        "'" + malo + "', " +
                        "'" + makho + "'  ," +
                        "'" + soluong + "', " +
                        "'" + po + "', " +
                        "'" + dn + "', " +
                        "'" + th + "', " +
                        "'" + trangthai + "', " +
                        "'" + status + "')";
                database.QuerryData(sql);
                Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                edt_soct.setText("");
                edtNgay.setText("");
                edtMavt.setText("");
                edtMavitri.setText("");
                edtMalo.setText("");
                edtKhohang.setText("");
                edt_soluong.setText("");
                edtPhieuPO.setText("");
                edtPhieuxh.setText("");
                edtPhieuth.setText("");
                edt_trangthai.setText("");
                edt_ghichu.setText("");
            }
        });
    }

    private void init() {
        btn_save = view.findViewById(R.id.btn_save);
        edt_soct = view.findViewById(R.id.soct);
        sp_magd = view.findViewById(R.id.sp_magd);
        sp_mact = view.findViewById(R.id.sp_mact);
        edtNgay = view.findViewById(R.id.ngay);
        edtMavt = view.findViewById(R.id.mavattu);
        edtMavitri = view.findViewById(R.id.mavitri);
        edtMalo = view.findViewById(R.id.malo);
        edtKhohang = view.findViewById(R.id.khohang);
        edtPhieuPO = view.findViewById(R.id.phieupo);
        edtPhieuxh = view.findViewById(R.id.ycxh);
        edtPhieuth = view.findViewById(R.id.ycth);
        edt_trangthai = view.findViewById(R.id.edt_trangthai);
        edt_ghichu = view.findViewById(R.id.edt_status);
        edt_soluong = view.findViewById(R.id.soluong);
    }
}