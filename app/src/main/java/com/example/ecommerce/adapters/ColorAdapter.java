package com.example.ecommerce.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerce.R;
import com.example.ecommerce.databinding.ViewholderColorBinding;

import java.util.ArrayList;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.Viewholder> {
    ArrayList<String> items;
    Context context;
    int selectedPosition = -1;
    int lastPosition = -1;

    public ColorAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ColorAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderColorBinding binding = ViewholderColorBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.Viewholder holder, int position) {

        holder.binding.getRoot().setOnClickListener(v -> {
            lastPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(lastPosition);
            notifyItemChanged(selectedPosition);
        });

        if (selectedPosition == holder.getAdapterPosition()) {
            Drawable unDrawable = AppCompatResources.getDrawable(context, R.drawable.color_selected);
            Glide.with(context).load(unDrawable).apply(RequestOptions.circleCropTransform()).into(holder.binding.colorLayouth);
        } else {

            Drawable unDrawable = AppCompatResources.getDrawable(context, R.drawable.color_selected);
            Drawable wrappedDrawable = DrawableCompat.wrap(unDrawable);
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(items.get(position)));
            Glide.with(context).load(unDrawable).apply(RequestOptions.circleCropTransform()).into(holder.binding.colorLayouth);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderColorBinding binding;

        public Viewholder(ViewholderColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
