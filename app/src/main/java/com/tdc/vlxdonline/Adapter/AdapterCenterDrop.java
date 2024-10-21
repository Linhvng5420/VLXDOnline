package com.tdc.vlxdonline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tdc.vlxdonline.R;

import java.util.ArrayList;

public class AdapterCenterDrop extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<String> data;

    public AdapterCenterDrop(@NonNull Context context, int resource, ArrayList<String> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        TextView tv = convertView.findViewById(R.id.tv_center_item);
        tv.setText(data.get(position));
        return convertView;
    }
}
