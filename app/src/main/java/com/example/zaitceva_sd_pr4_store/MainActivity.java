package com.example.zaitceva_sd_pr4_store;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zaitceva_sd_pr4_store.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Product> products = new ArrayList<>();
    BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //создаём адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, products, binding.footer.textViewFooter);

        //настраиваем список
        binding.listViewMain.setAdapter(boxAdapter);
    }

    //генерируем данные для адаптера
    void fillData() {
        int[] drawableResources = {R.drawable.plant1, R.drawable.plant2, R.drawable.plant3, R.drawable.plant4, R.drawable.plant5, R.drawable.plant6, R.drawable.plant7, R.drawable.plant8, R.drawable.plant9, R.drawable.plant10};
        String[] plantNames = {"Кротон", "Фикус бенджамина", "Гибискус", "Драцена", "Шеффлера", "Юкка", "Корделина", "Циперус", "Диффенбахия", "Монстера"};
        for (int i = 0; i < 5; i++) {
            products.add(new Product(i + 1, plantNames[i],(i + 1) * 250,
                    drawableResources[i], false));
        }
    }

    //выводим информацию о корзине
    public void showCart(View view) {
        ArrayList<Product> selectedProducts = boxAdapter.getBox();
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        intent.putParcelableArrayListExtra("selectedProducts", selectedProducts);
        startActivity(intent);
    }

    public void countSelectedProducts() {
        TextView textViewFooter = (TextView) findViewById(R.id.textViewFooter);
        ArrayList<Product> selectedProducts = boxAdapter.getBox();
        textViewFooter.setText(String.valueOf(selectedProducts.size()));
    }
}
