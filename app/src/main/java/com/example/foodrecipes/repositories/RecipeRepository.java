package com.example.foodrecipes.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.data.RecipeApiClient;
import com.example.foodrecipes.model.Recipe;

import java.util.List;

public class RecipeRepository {

    public static RecipeRepository instance;
    public RecipeApiClient recipeApiClient;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }

        return instance;
    }

    private RecipeRepository() {
        recipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipeApiClient.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        recipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void cancelRequest(){
        recipeApiClient.cancelRequest();
    }

}
