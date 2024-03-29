package com.example.expensestracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.expensestracker.R;
import com.example.expensestracker.model.spinneritem;

import java.util.ArrayList;

public class spinneraddapter extends ArrayAdapter<spinneritem> {
    public spinneraddapter(Context context, ArrayList<spinneritem> countryList) {
        super(context, 0, countryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }





    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.customspinner, parent, false
            );
        }

        ImageView kind_img = convertView.findViewById(R.id.imageView);
        TextView kind = convertView.findViewById(R.id.textView);

        spinneritem currentItem = getItem(position);

        if (currentItem != null) {
            kind_img.setImageResource(currentItem.getKind_img());
            kind.setText(currentItem.getKind());
        }

        return convertView;
    }
}
