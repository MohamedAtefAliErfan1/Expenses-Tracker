package com.example.expensestracker.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expensestracker.R;

public class customspinnerclass extends BaseAdapter {
    Context context;
    int kinds[];
    String[] kindtext;
    LayoutInflater inflter;

    public customspinnerclass(Context context, int[] kinds, String[] countryNames) {
        this.context = context;
        this.kinds = kinds;
        this.kindtext = countryNames;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.customspinner,parent,false);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(kinds[position]);
        names.setText(kindtext[position]);
        return view;
    }
}
