package com.example.foodrecipes.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodrecipes.R;
import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;

    private List<Recipe> recipeList;
    private OnRecipeListener onRecipeListener;

    public RecipeAdapter(OnRecipeListener onRecipeListener) {
        this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        if (viewType == LOADING_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading_list, parent, false);
            return new LoadingViewHolder(view);
        } else if (viewType == CATEGORY_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_list, parent, false);
            return new CategoryViewHolder(view, onRecipeListener);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe_list, parent, false);
        return new RecipeViewHolder(view, onRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == RECIPE_TYPE) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(recipeList.get(position).getImageUrl())           //  HEREEEE
                    .into(((RecipeViewHolder) holder).imgPoster);

            ((RecipeViewHolder) holder).tvTitle.setText(recipeList.get(position).getTitle());
            ((RecipeViewHolder) holder).tvPublisher.setText(recipeList.get(position).getPublisher());
            ((RecipeViewHolder) holder).tvPublisher.setText(String.valueOf(Math.round(recipeList.get(position).getSocialRank())));
        } else if (itemViewType == CATEGORY_TYPE) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Uri path = Uri.parse("android.resource://com.example.foodrecipes/drawable/" + recipeList.get(position).getImageUrl());

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(path)           //  HEREEEE
                    .into(((CategoryViewHolder) holder).categoryImage);

            ((CategoryViewHolder) holder).categoryTitle.setText(recipeList.get(position).getTitle());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (recipeList.get(position).getSocialRank() == -1) {
            return CATEGORY_TYPE;
        } else if (recipeList.get(position).getTitle().equals("LOADING...")) {
            return LOADING_TYPE;
        } else {
            return RECIPE_TYPE;
        }
    }

    public void displayLoading() {
        if (!isLoading()) {
            Recipe recipe = new Recipe();
            recipe.setTitle("LOADING...");
            List<Recipe> loadingList = new ArrayList<>();
            loadingList.add(recipe);
            recipeList = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (recipeList != null) {
            if (recipeList.size() > 0) {
                if (recipeList.get(recipeList.size() - 1).getTitle().equals("LOADING...")) {
                    return true;
                }
            }
        }

        return false;
    }

    public void displaySearchCategories() {
        List<Recipe> categories = new ArrayList<>();
        for (int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            recipe.setImageUrl(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
            recipe.setSocialRank(-1);
            categories.add(recipe);
        }
        recipeList = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (recipeList != null) {
            return recipeList.size();
        }

        return 0;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipeList = recipes;
        notifyDataSetChanged();
    }
}
















