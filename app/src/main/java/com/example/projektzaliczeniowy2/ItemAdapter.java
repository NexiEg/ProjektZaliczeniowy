package com.example.projektzaliczeniowy2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> items;
    LayoutInflater layoutInflater;
    ArrayList<Integer> images;
    TextView textView;
    ImageView imageView;

    int[] pcty;
    String [] opisy;

    public ItemAdapter(Context context, ArrayList<Integer> images, ArrayList<String> items){
        super();
        this.context = context;

        this.items = items;
        this.images = images;


//        this.pcty = pcty;
//        this.opisy = opisy;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.list_view, null);
        textView = view.findViewById(R.id.textView);
        imageView = view.findViewById(R.id.imageView);

        textView.setText(items.get(position));
        imageView.setImageResource(images.get(position));

//        imageView.setImageResource(pcty[position]);
//        textView.setText(opisy[position]);
        return view;
    }
}
