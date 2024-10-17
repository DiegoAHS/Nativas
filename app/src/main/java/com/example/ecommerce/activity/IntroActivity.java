package com.example.ecommerce.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecommerce.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.startBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}