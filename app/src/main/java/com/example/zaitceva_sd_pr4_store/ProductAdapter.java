package com.example.zaitceva_sd_pr4_store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Product> objects;
    ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        objects = products;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //количество элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    //элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    //id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    //пункт списка
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //используем созданные, но не используемые View
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_cart, parent, false);
        }
        Product product = getProduct(position);

        //заполняем View
        ((TextView) view.findViewById(R.id.textViewId)).setText(product.id + "");
        ((TextView) view.findViewById(R.id.textViewDescription)).setText(product.name);
        ((TextView) view.findViewById(R.id.textViewPrice)).setText(product.price + "");
        ((ImageView) view.findViewById(R.id.imageViewItem)).setImageResource(product.image);

        return view;
    }

    //товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }
}
