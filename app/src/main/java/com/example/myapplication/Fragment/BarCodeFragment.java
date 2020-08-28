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

import androidx.fragment.app.Fragment;

import com.example.myapplication.DatabaseManager;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.models.Barcode;
import com.example.myapplication.models.GiaoDich;

import java.util.ArrayList;
import java.util.List;

public class BarCodeFragment extends Fragment {
    View view;
    EditText edt_soluong, edt_trangthai, edt_ghichu, edt_soct, edtNgay, edtMavt, edtMavitri, edtMalo, edtKhohang, edtPhieuPO, edtPhieuxh, edtPhieuth;
    Spinner sp_mact, sp_magd;
    Button btn_save;
    ArrayAdapter adapter;
    ArrayList<String> arrayList;
    private List<Barcode> barcodeList;
    final DatabaseManager db = new DatabaseManager(getContext());
    public BarCodeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_barcode, container, false);
        arrayList = new ArrayList<>();
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter.notifyDataSetChanged();
        init();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Barcode barcode = createBarcode();
                if (barcode != null) {
                    db.addBarcode(barcode);
                }
                barcodeList.clear();
                barcodeList.addAll(db.getAllBarcode());
            }
        });
        //  loadData();
        return view;
    }
    private Barcode createBarcode() {
        String mact = sp_mact.getSelectedItem().toString();
        String magd = sp_magd.getSelectedItem().toString();
        String tengd = edt_soct.getText().toString();
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
        Barcode barcode = new Barcode(mact, magd, tengd, ngay, mavatu,mavitri,malo,makho,soluong, po, dn, th, trangthai, status);
        return barcode;
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