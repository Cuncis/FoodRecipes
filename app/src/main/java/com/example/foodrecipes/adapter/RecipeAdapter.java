package com.example.foodrecipes.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodrecipes.R;
import com.example.foodrecipes.model.Recipes;
import com.example.foodrecipes.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int RECIPE_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;

    private List<Recipes> recipesList;
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
                    .load(recipesList.get(position).getImageUrl())           //  HEREEEE
                    .into(((RecipeViewHolder) holder).imgPoster);

            ((RecipeViewHolder) holder).tvTitle.setText(recipesList.get(position).getTitle());
            ((RecipeViewHolder) holder).tvPublisher.setText(recipesList.get(position).getPublisher());
            ((RecipeViewHolder) holder).tvSocialScore.setText(String.valueOf(Math.round(recipesList.get(position).getSocialRank())));
        } else if (itemViewType == CATEGORY_TYPE) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Uri path = Uri.parse("android.resource://com.example.foodrecipes/drawable/" + recipesList.get(position).getImageUrl());

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(options)
                    .load(path)           //  HEREEEE
                    .into(((CategoryViewHolder) holder).categoryImage);

            ((CategoryViewHolder) holder).categoryTitle.setText(recipesList.get(position).getTitle());
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (recipesList.get(position).getSocialRank() == -1) {
            return CATEGORY_TYPE;
        } else if (recipesList.get(position).getTitle().equals("LOADING...")) {
            return LOADING_TYPE;
        }  else if (position == recipesList.size()-1 && position != 0 && !recipesList.get(position).getTitle().equals("EXHAUSTED...")) {
            return LOADING_TYPE;
        } else {
            return RECIPE_TYPE;
        }
    }

    public void displayLoading() {
        if (!isLoading()) {
            Recipes recipes = new Recipes();
            recipes.setTitle("LOADING...");
            List<Recipes> loadingList = new ArrayList<>();
            loadingList.add(recipes);
            recipesList = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if (recipesList != null) {
            if (recipesList.size() > 0) {
                if (recipesList.get(recipesList.size() - 1).getTitle().equals("LOADING...")) {
                    return true;
                }
            }
        }

        return false;
    }

    public void displaySearchCategories() {
        List<Recipes> categories = new ArrayList<>();
        for (int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++) {
            Recipes recipes = new Recipes();
            recipes.setTitle(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            recipes.setImageUrl(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
            recipes.setSocialRank(-1);
            categories.add(recipes);
        }
        recipesList = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (recipesList != null) {
            return recipesList.size();
        }

        return 0;
    }

    public void setRecipes(List<Recipes> recipes) {
        this.recipesList = recipes;
        notifyDataSetChanged();
    }

    public Recipes getSelectedRecipe(int position) {
        if (recipesList != null) {
            if (recipesList.size() > 0) {
                return recipesList.get(position);
            }
        }
        return null;
    }
}
















