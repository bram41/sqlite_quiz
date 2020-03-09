package com.bram41.tugasquizbackend.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bram41.tugasquizbackend.R;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView pertama = (TextView) convertView.findViewById(R.id.pertama);
        TextView kedua = (TextView) convertView.findViewById(R.id.kedua);
        TextView ketiga = (TextView) convertView.findViewById(R.id.ketiga);
        TextView soal = (TextView) convertView.findViewById(R.id.soal);
        TextView benar = (TextView) convertView.findViewById(R.id.benar);

        Data data = items.get(position);

        id.setText(data.getId());
        soal.setText(data.getSoal());
        benar.setText(data.getBenar());
        pertama.setText(data.getPertama());
        kedua.setText(data.getKedua());
        ketiga.setText(data.getKetiga());

        return convertView;
    }
}