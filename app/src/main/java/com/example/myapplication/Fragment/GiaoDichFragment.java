package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.myapplication.DatabaseManager;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomAdapterGD;
import com.example.myapplication.adapter.CustomAdapterTD;
import com.example.myapplication.models.GiaoDich;
import com.example.myapplication.models.TuDong;

import java.util.List;

public class GiaoDichFragment extends Fragment {
    View view;
    EditText edt_id, edt_mact, edt_magd, edt_tengd;
    Button btn_luu, btn_sua;
    ListView lvgd;
    private CustomAdapterGD customAdapterGD;
    private List<GiaoDich> giaoDichList;
    public GiaoDichFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_giao_dich, container, false);
        final DatabaseManager db = new DatabaseManager(getContext());
        init();

        giaoDichList = db.getAllGiaoDich();
        setAdapter();
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoDich giaoDich = createGiaoDich();
                if (giaoDich != null) {
                    db.addGiaoDich(giaoDich);
                }
                giaoDichList.clear();
                giaoDichList.addAll(db.getAllGiaoDich());
                setAdapter();

            }
        });
        lvgd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GiaoDich giaoDich = giaoDichList.get(position);
                edt_id.setText(String.valueOf(giaoDich.getId()));
                edt_mact.setText(giaoDich.getMaCT());
                edt_magd.setText(giaoDich.getMaGD());
                edt_tengd.setText(giaoDich.getTenGD());
                btn_luu.setEnabled(false);
                btn_sua.setEnabled(true);
            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiaoDich giaoDich = new GiaoDich();
                giaoDich.setId(Integer.parseInt(String.valueOf(edt_id.getText())));
                giaoDich.setMaCT(edt_mact.getText() + "");
                giaoDich.setMaGD(edt_magd.getText() + "");
                giaoDich.setTenGD(edt_tengd.getText() + "");
                int result = db.updateGiaoDich(giaoDich);
                if (result > 0) {
                    giaoDichList.clear();
                    giaoDichList.addAll(db.getAllGiaoDich());
                    customAdapterGD.notifyDataSetChanged();
                }
                btn_luu.setEnabled(true);
                btn_sua.setEnabled(false);
            }
        });
        lvgd.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                GiaoDich giaoDich = giaoDichList.get(position);
                int result = db.deleteGiaoDich(giaoDich.getId());
                if (result > 0) {
                    Toast.makeText(getContext(), "Delete Successfuly", Toast.LENGTH_SHORT).show();
                    giaoDichList.clear();
                    giaoDichList.addAll(db.getAllGiaoDich());
                    customAdapterGD.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        return view;
    }

    private void init() {
        edt_id = view.findViewById(R.id.idgd);
        edt_mact = view.findViewById(R.id.mact_gd);
        edt_magd = view.findViewById(R.id.magd);
        edt_tengd = view.findViewById(R.id.tengd);
        btn_luu = view.findViewById(R.id.btn_luugd);
        btn_sua = view.findViewById(R.id.btn_suagd);
        lvgd = view.findViewById(R.id.lvgd);
    }

    private GiaoDich createGiaoDich() {
        String mact = edt_mact.getText().toString();
        String magd = edt_magd.getText().toString();
        String tengd = edt_tengd.getText().toString();
        GiaoDich giaoDich = new GiaoDich(mact, magd, tengd);
        return giaoDich;
    }

    private void setAdapter() {
        if (customAdapterGD == null) {
            customAdapterGD = new CustomAdapterGD(getContext(), R.layout.item_giao_dich, giaoDichList);
            lvgd.setAdapter(customAdapterGD);
        } else {
            customAdapterGD.notifyDataSetChanged();
            lvgd.setSelection(customAdapterGD.getCount() - 1);
        }
    }

}