package com.example.foodrecipes.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView tvTitle, tvPublisher, tvSocialScore;
    ImageView imgPoster;
    OnRecipeListener recipeListener;

    public RecipeViewHolder(@NonNull View itemView, OnRecipeListener recipeListener) {
        super(itemView);
        this.recipeListener = recipeListener;
        tvTitle = itemView.findViewById(R.id.recipe_title);
        tvPublisher = itemView.findViewById(R.id.recipe_publisher);
        tvSocialScore = itemView.findViewById(R.id.recipe_social_score);
        imgPoster = itemView.findViewById(R.id.recipe_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        recipeListener.onRecipeClick(getAdapterPosition());
    }
}
