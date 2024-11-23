package com.example.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.ecommerce.adapters.CardAdapter;
import com.example.ecommerce.databinding.ActivityCartBinding;
import com.example.ecommerce.helper.ManagmentCart;

public class CartActivity extends BaseActivity {

    private ActivityCartBinding binding;
    private double tax;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        calculatorCart();
        setVariable();
        initCartList();
        initBtn();

    }

    private void initBtn() {
        binding.checkoutBtn.setOnClickListener(v->startActivity(new Intent(CartActivity.this,paypalActivity.class)));
    }

    private void initCartList() {
        if(managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scroolViewCart.setVisibility(View.GONE);
        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scroolViewCart.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.cartView.setAdapter(new CardAdapter(managmentCart.getListCart(),this,()-> calculatorCart()));
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener( v -> finish());
    }

    private void calculatorCart(){
        double percentTxt = 0.02;
        double delievery = 10;

        tax = Math.round((managmentCart.getTotalFee()*percentTxt * 100)) / 100;
        double total = Math.round((managmentCart.getTotalFee() + tax + delievery) * 100)/100;
        double itemTotal = Math.round((managmentCart.getTotalFee() * 100)) / 100;

        binding.totalFeeTxt.setText("$"+itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.delieveryTxt.setText("$" + delievery);
        binding.totalTxt.setText("$"+total);


    }
}