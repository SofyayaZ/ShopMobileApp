package com.example.zaitceva_sd_pr4_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.zaitceva_sd_pr4_store.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        ArrayList<Product> selectedProducts = getIntent().getParcelableArrayListExtra("selectedProducts");
        ProductAdapter adapter = new ProductAdapter(this, selectedProducts);
        ListView listViewCart = (ListView) findViewById(R.id.listViewCart);
        listViewCart.setAdapter(adapter);

        //считаем сумму всех товаров
        int totalPrice = calculateTotalPrice(selectedProducts);
        binding.totalPriceCart.setText("Итого: " + totalPrice);
    }
    private int calculateTotalPrice(ArrayList<Product> products) {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.price;
        }
        return totalPrice;
    }
}
