package com.example.foodrecipes.repositories;

import androidx.lifecycle.LiveData;

import com.example.foodrecipes.data.RecipeApiClient;
import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.model.Recipes;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient recipeApiClient;
    private String query;
    private int pageNumber;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }

        return instance;
    }

    private RecipeRepository() {
        recipeApiClient = RecipeApiClient.getInstance();
    }

    public LiveData<List<Recipes>> getRecipes() {
        return recipeApiClient.getRecipes();
    }

    public LiveData<Recipe> getRecipe() {
        return recipeApiClient.getRecipe();
    }

    public void searchRecipeById(String recipeId) {
        recipeApiClient.searchRecipeId(recipeId);
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (pageNumber == 0) {
            pageNumber = 1;
        }
        this.query = query;
        this.pageNumber = pageNumber;
        recipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage() {
        searchRecipesApi(query, pageNumber + 1);
    }

    public void cancelRequest(){
        recipeApiClient.cancelRequest();
    }

}
