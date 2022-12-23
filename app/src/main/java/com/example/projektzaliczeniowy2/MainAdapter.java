package com.example.projektzaliczeniowy2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> dane;


    public MainAdapter(Context context, ArrayList<String> dane) {
        this.context = context;
        this.dane = dane;
    }




    @Override
    public int getCount() {
        return dane.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.row, viewGroup, false);

        TextView textView = view.findViewById(R.id.kafelek);

        textView.setText(dane.get(i));


        return view;
    }
}
