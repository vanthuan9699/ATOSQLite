package com.example.myapplication.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Database.Database;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TuDongFragment extends Fragment {
    View view;
    private EditText edtMact, edtTenct;
    private Button btnLuu, btnSua;
    private ListView lv;
    public Database database;
    List<String> tudonglist = new ArrayList<>();
    ArrayAdapter adapter;
    public TuDongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tu_dong, container, false);
        edtMact = view.findViewById(R.id.edt_mact);
        edtTenct = view.findViewById(R.id.edt_tenct);
        btnLuu = view.findViewById(R.id.btn_luu);
        lv = view.findViewById(R.id.lv1);
//        btnSua = view.findViewById(R.id.btn_sua);

        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, tudonglist);
        lv.setAdapter(adapter);

        database = new Database(getContext(), "QuanLy.sqlite", null, 1);
        database.QuerryData("create table if not exists zcdmct_tudong(Ma_ct char(3), Ten_ct nvarchar(128))");
        getTuDong();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mact = edtMact.getText().toString();
                String tenct = edtTenct.getText().toString();

                if (mact.equals("") || tenct.equals("")){
                    Toast.makeText(getContext(), "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    database.QuerryData("insert into zcdmct_tudong values(' " + mact + " ', '" + tenct + "')");
                    Toast.makeText(getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    getTuDong();
                    adapter.notifyDataSetChanged();
                }
                edtMact.setText("");
                edtTenct.setText("");
            }
        });
        return view;
    }
    private void getTuDong() {
        Cursor cursor = database.GetData("SELECT * FROM zcdmct_tudong");
        tudonglist.clear();
        while (cursor.moveToNext()) {
            String mact = cursor.getString(0);
            String tenct = cursor.getString(1);
            tudonglist.add(mact + "        " + tenct);
        }
        adapter.notifyDataSetChanged();
    }
}
