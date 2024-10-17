package com.example.ecommerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerce.databinding.ViewholderCartBinding;
import com.example.ecommerce.domain.ItemsDomain;
import com.example.ecommerce.helper.ChangeNumberItemsListener;
import com.example.ecommerce.helper.ManagmentCart;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Viewholder> {


    ArrayList<ItemsDomain> listItemsSelected;
    ChangeNumberItemsListener changeNumberItemsListener;
    private ManagmentCart managmentCart;

    public CardAdapter(ArrayList<ItemsDomain> listItemsSelected, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemsSelected = listItemsSelected;
        this.changeNumberItemsListener = changeNumberItemsListener;
        managmentCart = new ManagmentCart(context);
    }

    @NonNull
    @Override
    public CardAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderCartBinding binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.Viewholder holder, int position) {
        holder.binding.tiitleTxt.setText(listItemsSelected.get(position).getTitle());
        holder.binding.feeEachItem.setText("$" + listItemsSelected.get(position).getPrice());
        holder.binding.totalEachItem.setText("$" + Math.round(listItemsSelected.get(position).getNumberInCart() * listItemsSelected.get(position).getPrice()));
        holder.binding.numberItemTxt.setText(String.valueOf(listItemsSelected.get(position).getNumberInCart()));

        Glide.with(holder.itemView.getContext()).load(listItemsSelected.get(position).getPicUrl().get(0)).apply(RequestOptions.circleCropTransform()).into(holder.binding.pic);

        holder.binding.plusCartBtn.setOnClickListener(v -> managmentCart.plusItem(listItemsSelected,position,()->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));

        holder.binding.minusCardBtn.setOnClickListener(v -> managmentCart.minusItem(listItemsSelected,position,()->{
            notifyDataSetChanged();
            changeNumberItemsListener.changed();
        }));


    }

    @Override
    public int getItemCount() {
        return listItemsSelected.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderCartBinding binding;

        public Viewholder(ViewholderCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
