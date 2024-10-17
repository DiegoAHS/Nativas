package com.example.ecommerce.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.R;
import com.example.ecommerce.adapters.ColorAdapter;
import com.example.ecommerce.adapters.SizeAdapter;
import com.example.ecommerce.adapters.SliderAdapter;
import com.example.ecommerce.databinding.ActivityDetailBinding;
import com.example.ecommerce.domain.ItemsDomain;
import com.example.ecommerce.domain.SliderItems;
import com.example.ecommerce.helper.ManagmentCart;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private ItemsDomain object;
    private int numberOrder;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);
        getBundles();
        initBanners();
        initSize();
        initColor();

    }

    private void initColor() {
        ArrayList<String> list = new ArrayList<>();
        list.add("#006fc4");
        list.add("#daa048");
        list.add("#398d41");
        list.add("#0c3c72");
        list.add("#829db5");

        binding.recyclerColor.setAdapter(new ColorAdapter(list));
        binding.recyclerColor.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void initSize() {
        ArrayList<String> list = new ArrayList<>();
        list.add("XS");
        list.add("S");
        list.add("M");
        list.add("L");
        list.add("XL");

        binding.reciclerSize.setAdapter(new SizeAdapter(list));
        binding.reciclerSize.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void initBanners(){
        ArrayList<SliderItems>sliderItems = new ArrayList<>();
        for (int i = 0; i < object.getPicUrl().size(); i++) {
            sliderItems.add(new SliderItems(object.getPicUrl().get(i)));
        }
        binding.viewpagerSlider.setAdapter(new SliderAdapter(sliderItems, binding.viewpagerSlider));
        binding.viewpagerSlider.setClipToPadding(false);
        binding.viewpagerSlider.setClipChildren(false);
        binding.viewpagerSlider.setOffscreenPageLimit(3);
        binding.viewpagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
    }

    private void getBundles() {
        object = (ItemsDomain) getIntent().getSerializableExtra("object");
        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("$"+object.getPrice());
        binding.ratingBar.setRating((float) object.getRating());
        binding.ratingTxt.setText(object.getRating()+" Rating");
        binding.descriptionTxt.setText(object.getDescription());

        binding.addtoCartBtn.setOnClickListener(v -> {
            object.setNumberInCart(numberOrder);
            managmentCart.insertItem(object);
        });

        binding.backBtn.setOnClickListener(v -> finish());

    }
}