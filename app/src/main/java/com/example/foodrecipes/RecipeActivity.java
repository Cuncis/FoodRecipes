package com.example.foodrecipes;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
                    Log.d(TAG, "onChanged: -----------------------------");
                    Log.d(TAG, "onChanged: " + recipe.getTitle());
                    for (String ingredients: recipe.getIngredients()) {
                        Log.d(TAG, "onChanged: " + ingredients);
                    }
                }
            }
        });
    }

}






















