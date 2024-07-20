package com.example.mealmate;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.formatter.IFillFormatter;

import java.util.List;
import java.util.Objects;


public class FridgeAdapter extends RecyclerView.Adapter<FridgeAdapter.ViewHolder> {
    List<FridgeModel> list;
    Context context;

    public FridgeAdapter(List<FridgeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<FridgeModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fridge, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FridgeModel fridgeModel = list.get(position);
        holder.tvName.setText(fridgeModel.getName());
        holder.tvDate.setText(fridgeModel.getDate());
        holder.tvQuantity.setText(fridgeModel.getQuantity());
        if (Objects.equals(fridgeModel.getType(), "Meat")) {
            holder.ivImage.setImageResource(R.drawable.meat);
        }else if (Objects.equals(fridgeModel.getType(), "Vegetables")) {
            holder.ivImage.setImageResource(R.drawable.vegetable);
        }else if (Objects.equals(fridgeModel.getType(), "Fruits")) {
            holder.ivImage.setImageResource(R.drawable.fruits);
        }else if (Objects.equals(fridgeModel.getType(), "Oils")) {
            holder.ivImage.setImageResource(R.drawable.oil);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddFridgeActivity.class);
                intent.putExtra("data", fridgeModel);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQuantity, tvDate;
        ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
