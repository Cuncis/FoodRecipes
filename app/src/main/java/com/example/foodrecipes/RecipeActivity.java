package com.example.foodrecipes;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.model.Recipes;
import com.example.foodrecipes.viewmodel.RecipeViewModel;

public class RecipeActivity extends BaseActivity {
    private static final String TAG = "_RecipeActivity";

    private ImageView recipeImage;
    private TextView recipeTitle, recipeRank;
    private LinearLayout recipeIngredienContainer;
    private ScrollView scrollView;

    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recipeImage = findViewById(R.id.recipe_image);
        recipeTitle = findViewById(R.id.recipe_title);
        recipeRank = findViewById(R.id.recipe_social_score);
        recipeIngredienContainer = findViewById(R.id.ingredients_container);
        scrollView = findViewById(R.id.parent);

        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        showProgressBar(true);
        subscribeObservers();
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("recipe")) {
            Recipes recipes = getIntent().getParcelableExtra("recipe");
            Log.d(TAG, "getIncomingIntent: " + recipes.getTitle());
            recipeViewModel.searchRecipeById(recipes.getRecipeId());
        }
    }

    private void subscribeObservers() {
        recipeViewModel.getRecipe().observe(this, new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                if (recipe != null) {
                    if (recipe.getRecipeId().equals(recipeViewModel.getRecipeId())) {
                        setRecipeProperties(recipe);
                        recipeViewModel.setDidRetrieveRecipe(true);
                    }
                }
            }
        });

        recipeViewModel.isRecipeTimedOut().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean && !recipeViewModel.isDidRetrieveRecipe()) {
                    Log.d(TAG, "onChanged: Timed Out..");
                    displayErrorScreen("Error retrieving data. Check Network Connection...");
                }
            }
        });
    }

    private void displayErrorScreen(String errorMessage) {
        recipeTitle.setText("Error Retrieving recipe...");
        recipeRank.setText("");
        TextView textView = new TextView(this);
        if (!errorMessage.equals("")) {
            textView.setText(errorMessage);
        } else {
            textView.setText("Error");
        }
        textView.setTextSize(15);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        recipeIngredienContainer.addView(textView);

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(R.drawable.ic_launcher_background)
                .into(recipeImage);

        showParent();
        showProgressBar(false);
    }

    private void setRecipeProperties(Recipe recipe) {
        if (recipe != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(recipe.getImageUrl())
                    .into(recipeImage);

            recipeTitle.setText(recipe.getTitle());
            recipeRank.setText(String.valueOf(Math.round(recipe.getSocialRank())));

            recipeIngredienContainer.removeAllViews();
            for (String ingredients: recipe.getIngredients()) {
                TextView textView = new TextView(this);
                textView.setText(ingredients);
                textView.setTextSize(15);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));

                recipeIngredienContainer.addView(textView);
            }
        }

        showParent();
        showProgressBar(false);
    }

    private void showParent() {
        scrollView.setVisibility(View.VISIBLE);
    }

}






















