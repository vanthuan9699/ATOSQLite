package com.example.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.DatabaseManager;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CustomAdapterTD;
import com.example.myapplication.models.TuDong;

import java.util.List;

public class TuDongFragment extends Fragment {
    View view;
    private EditText edtMact, edtTenct, edtid;
    private Button btnLuu, btnSua;
    private ListView lv;
    private CustomAdapterTD customAdapterTD;
    private List<TuDong> tuDongList;

    public TuDongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tu_dong, container, false);
        final DatabaseManager db = new DatabaseManager(getContext());
        edtMact = view.findViewById(R.id.edt_mact);
        edtTenct = view.findViewById(R.id.edt_tenct);
        btnLuu = view.findViewById(R.id.btn_luu);
        lv = view.findViewById(R.id.lv1);
        edtid = view.findViewById(R.id.id);
        btnSua = view.findViewById(R.id.btn_sua);

        tuDongList = db.getAllTuDong();
        setAdapter();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TuDong tuDong = createTuDong();
                if (tuDong != null) {
                    db.addTuDong(tuDong);
                }
                tuDongList.clear();
                tuDongList.addAll(db.getAllTuDong());
                setAdapter();

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TuDong tuDong = tuDongList.get(position);
                edtid.setText(String.valueOf(tuDong.getId()));
                edtMact.setText(tuDong.getMaCT());
                edtTenct.setText(tuDong.getTenCT());
                btnLuu.setEnabled(false);
                btnSua.setEnabled(true);
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TuDong tuDong = new TuDong();
                tuDong.setId(Integer.parseInt(String.valueOf(edtid.getText())));
                tuDong.setMaCT(edtMact.getText() + "");
                tuDong.setTenCT(edtTenct.getText() + "");
                int result = db.updateTuDong(tuDong);
                if (result > 0) {
                    tuDongList.clear();
                    tuDongList.addAll(db.getAllTuDong());
                    customAdapterTD.notifyDataSetChanged();
                }
                btnLuu.setEnabled(true);
                btnSua.setEnabled(false);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TuDong tuDong = tuDongList.get(position);
                int result = db.deleteTuDong(tuDong.getId());
                if (result > 0) {
                    Toast.makeText(getContext(), "Delete Successfuly", Toast.LENGTH_SHORT).show();
                    tuDongList.clear();
                    tuDongList.addAll(db.getAllTuDong());
                    customAdapterTD.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        return view;
    }
    private TuDong createTuDong() {
        String mact = edtMact.getText().toString();
        String tenct = edtTenct.getText().toString();
        TuDong tuDong = new TuDong(mact, tenct);
        return tuDong;
    }
    private void setAdapter() {
        if (customAdapterTD == null) {
            customAdapterTD = new CustomAdapterTD(getContext(), R.layout.item_tu_dong, tuDongList);
            lv.setAdapter(customAdapterTD);
        } else {
            customAdapterTD.notifyDataSetChanged();
            lv.setSelection(customAdapterTD.getCount() - 1);
        }
    }
}