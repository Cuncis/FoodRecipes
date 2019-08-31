package com.example.foodrecipes;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodrecipes.data.ApiClient;
import com.example.foodrecipes.data.RecipeApi;
import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.model.RecipeResponse;
import com.example.foodrecipes.utils.Constants;
import com.example.foodrecipes.utils.Testing;
import com.example.foodrecipes.viewmodel.RecipeListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends BaseActivity {
    private static final String TAG = "_RecipeListActivity";

    private RecipeListViewModel recipeListViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);

        recipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRetrofitRequest();
            }
        });


        subscribeObservers();

        // test
    }

    private void subscribeObservers() {
        recipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    Testing.printRecipes(recipes, TAG);
                }

            }
        });
    }

    public void searchRecipesApi(String query, int pageNumber) {
        recipeListViewModel.searchRecipesApi(query, pageNumber);
    }

    private void testRetrofitRequest() {
        searchRecipesApi("chicken breast", 1);
    }

}
