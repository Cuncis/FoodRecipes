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

        subscribeObservers();

//        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                testRetrofitRequest();
//            }
//        });

        // test
    }

    private void subscribeObservers() {
        recipeListViewModel.getRecipe().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {

            }
        });
    }

    private void testRetrofitRequest() {
        final RecipeApi recipeApi = ApiClient.getRecipeApi();

        Call<RecipeResponse> call = recipeApi.searchRecipe(Constants.API_KEY,
                "Chicken breast",
                "1");

        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    List<Recipe> recipes = response.body().getRecipes();
                    for (Recipe recipe: recipes) {
                        Log.d(TAG, "onResponse: title: " + recipe.getTitle());
                    }
                } else {
                    Log.d(TAG, "onResponse: Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
