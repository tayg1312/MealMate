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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
    List<RecipeModel> list;
    Context context;

    public RecipesAdapter(List<RecipeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(List<RecipeModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RecipeModel recipeModel = list.get(position);
        holder.tvName.setText(recipeModel.getName());
        holder.tvType.setText(recipeModel.getType());
        holder.tvReviews.setText(recipeModel.getReviews());
        holder.tvTime.setText("Total Time: "+recipeModel.getTime());
        holder.ratingBar.setRating(Float.parseFloat(String.valueOf(recipeModel.getRating())));
        holder.ivImage.setImageResource(recipeModel.getImage());
        if (list.size()-1 == position){
            holder.view.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeModel.getLink()));
                intent.putExtra("force_fullscreen", true);

                // Try to open in the YouTube app
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                } else {
                    // Fallback to opening in browser if YouTube app is not installed
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeModel.getLink()));
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvType, tvTime, tvReviews;
        RatingBar ratingBar;
        ImageView ivImage;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvReviews = itemView.findViewById(R.id.tvReviews);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ivImage = itemView.findViewById(R.id.ivImage);
            view = itemView.findViewById(R.id.view);
        }
    }
}
