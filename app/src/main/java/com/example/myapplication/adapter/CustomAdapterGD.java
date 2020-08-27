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
import com.example.myapplication.models.GiaoDich;

import java.util.List;

public class CustomAdapterGD extends ArrayAdapter<GiaoDich> {
    private Context context;
    private int resoure;
    private List<GiaoDich> giaoDichList;

    public CustomAdapterGD(@NonNull Context context, int resource, @NonNull List<GiaoDich> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.giaoDichList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        CustomAdapterGD.ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_giao_dich, parent, false);
            viewHolder = new CustomAdapterGD.ViewHolder();
            viewHolder.tvMact = convertView.findViewById(R.id.tv_mact);
            viewHolder.tvMagd = convertView.findViewById(R.id.tv_magd);
            viewHolder.tvTengd = convertView.findViewById(R.id.tv_tengd);
            convertView.setTag(viewHolder);
        }else {
            viewHolder =(CustomAdapterGD.ViewHolder) convertView.getTag();
        }
        GiaoDich giaoDich = giaoDichList.get(position);
        viewHolder.tvMact.setText(giaoDich.getMaCT());
        viewHolder.tvMagd.setText(giaoDich.getMaGD());
        viewHolder.tvTengd.setText(giaoDich.getMaGD());
        return convertView;
    }
    public class ViewHolder{
        private TextView tvMact, tvMagd, tvTengd;
    }
}