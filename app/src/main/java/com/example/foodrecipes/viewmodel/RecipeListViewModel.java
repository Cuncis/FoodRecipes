package com.example.foodrecipes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.repositories.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends ViewModel {

    private RecipeRepository recipeRepository;
    private boolean isViewingRecipes;
    private boolean isPerformingQuery;

    public RecipeListViewModel() {
        recipeRepository = RecipeRepository.getInstance();
        isPerformingQuery = false;
    }

    public LiveData<List<Recipe>> getRecipe() {
        return recipeRepository.getRecipes();
    }

    public void searchRecipesApi(String query, int pageNumber) {
        isViewingRecipes = true;
        isPerformingQuery = true;
        recipeRepository.searchRecipesApi(query, pageNumber);
    }

    public boolean isViewingRecipes() {
        return isViewingRecipes;
    }

    public void setIsViewingrecipes(boolean isViewingRecipes) {
        this.isViewingRecipes = isViewingRecipes;
    }

    public void setIsPerformingQuery(boolean performingQuery) {
        this.isPerformingQuery = performingQuery;
    }

    public boolean isPerformingQuery() {
        return isPerformingQuery;
    }

    public boolean onBackPressed() {
        if (isPerformingQuery) {
            // Cancel the query
            recipeRepository.cancelRequest();
            isPerformingQuery = false;
        }
        if (isViewingRecipes) {
            isViewingRecipes = false;
            return false;
        }

        return true;
    }

}
