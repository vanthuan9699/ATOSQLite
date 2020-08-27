package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.TuDong;

import java.util.List;

public class CustomAdapterTD extends ArrayAdapter<TuDong> {
    private Context context;
    private int resoure;
    private List<TuDong> tuDongList;
    public CustomAdapterTD(@NonNull Context context, int resource, @NonNull List<TuDong> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.tuDongList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tu_dong, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMact = convertView.findViewById(R.id.tvMact);
            viewHolder.tvTenct = convertView.findViewById(R.id.tvTenct);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        TuDong tuDong = tuDongList.get(position);
        viewHolder.tvMact.setText(tuDong.getMaCT());
        viewHolder.tvTenct.setText(tuDong.getTenCT());
        return convertView;
    }
    public class ViewHolder{
        private TextView tvMact, tvTenct;
    }
}
