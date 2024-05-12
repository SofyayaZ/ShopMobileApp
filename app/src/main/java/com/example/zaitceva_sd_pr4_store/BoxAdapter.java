package com.example.zaitceva_sd_pr4_store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zaitceva_sd_pr4_store.databinding.ItemBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Product> objects;
    TextView textViewFooter;
    private ItemBinding binding;
    BoxAdapter(Context context, ArrayList<Product> products, ItemBinding itemBinding) {
        this.context = context;
        objects = products;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.textViewFooter = textViewFooter;
        this.binding = itemBinding;
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
            view = layoutInflater.inflate(R.layout.item, parent, false);
        }
        Product product = getProduct(position);

        //заполняем View
        ((TextView) view.findViewById(R.id.textViewId)).setText(product.id + "");
        ((TextView) view.findViewById(R.id.textViewDescription)).setText(product.name);
        ((TextView) view.findViewById(R.id.textViewPrice)).setText(product.price + "");
        ((ImageView) view.findViewById(R.id.imageViewItem)).setImageResource(product.image);

        CheckBox checkBoxBuy = (CheckBox) view.findViewById(R.id.checkBoxItem);
        //присваиваем чекбоксу обработчик
        checkBoxBuy.setOnCheckedChangeListener(myCheckChangeList);
        //пишем позицию
        checkBoxBuy.setTag(position);
        //заполняем данными из товаров: в корзине или нет
        checkBoxBuy.setChecked(product.box);
        return view;
    }

    //товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    //содержимое корзины
    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product product : objects) {
            //если продукт в корзине
            if (product.box) {
                box.add(product);
            }
        }
        return box;
    }

    //обработчик для чекбоксов
    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //меняем данные товара (в корзине или нет)
            getProduct((Integer) buttonView.getTag()).box = isChecked;
            textViewFooter.setText("Количество выбранных позиций: " + getBox().size());
        }
    };
}
