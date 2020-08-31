package com.example.myapplication.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Database.Database;
import com.example.myapplication.R;


import java.util.ArrayList;
import java.util.List;

public class GiaoDichFragment extends Fragment {
    View view;
    EditText edt_id, edt_mact, edt_magd, edt_tengd;
    Button btn_luu, btn_sua;
    ListView lvgd;
    public Database database;
    List<String> giaodichlist = new ArrayList<>();
    ArrayAdapter adapter;

    public GiaoDichFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_giao_dich, container, false);
        init();
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, giaodichlist);
        lvgd.setAdapter(adapter);

        database = new Database(getContext(), "QuanLy.sqlite", null, 1);
        database.QuerryData("create table if not exists zcdmct_giaodich(Ma_ct char(3), Ma_gd char(3), Ten_gd nvarchar(128))");
        getGiaoDich();

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mact = edt_mact.getText().toString();
                String magd = edt_magd.getText().toString();
                String tengd = edt_tengd.getText().toString();

                if (mact.equals("") || magd.equals("") || tengd.equals("")) {
                    Toast.makeText(getContext(), "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    database.QuerryData("insert into zcdmct_giaodich values(' " + mact + " ', '" + magd + "', '" + tengd + "')");
                    Toast.makeText(getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    getGiaoDich();
                    adapter.notifyDataSetChanged();
                }
                edt_mact.setText("");
                edt_magd.setText("");
                edt_tengd.setText("");
            }
        });

        return view;
    }
    private void getGiaoDich() {
        Cursor cursor = database.GetData("SELECT * FROM zcdmct_giaodich");
        giaodichlist.clear();
        while (cursor.moveToNext()) {
            String mact = cursor.getString(0);
            String magd = cursor.getString(1);
            String tengd = cursor.getString(2);
            giaodichlist.add(mact + "        " + magd + "         " + tengd);
        }
        adapter.notifyDataSetChanged();
    }

    private void init() {

        edt_mact = view.findViewById(R.id.mact_gd);
        edt_magd = view.findViewById(R.id.magd);
        edt_tengd = view.findViewById(R.id.tengd);
        btn_luu = view.findViewById(R.id.btn_luugd);
//        btn_sua = view.findViewById(R.id.btn_suagd);
        lvgd = view.findViewById(R.id.lvgd);
    }


}