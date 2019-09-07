package com.example.foodrecipes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.repositories.RecipeRepository;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository recipeRepository;
    private String recipeId;
    private boolean didRetrieveRecipe;

    public RecipeViewModel() {
        recipeRepository = RecipeRepository.getInstance();
        didRetrieveRecipe = false;
    }

    public LiveData<Recipe> getRecipe() {
        return recipeRepository.getRecipe();
    }

    public LiveData<Boolean> isRecipeTimedOut() {
        return recipeRepository.isRecipeTimedOut();
    }

    public void searchRecipeById(String recipeId) {
        this.recipeId = recipeId;
        recipeRepository.searchRecipeById(recipeId);
    }

    public String getRecipeId() {
        return recipeId;
    }

    public boolean isDidRetrieveRecipe() {
        return didRetrieveRecipe;
    }

    public void setDidRetrieveRecipe(boolean didRetrieveRecipe) {
        this.didRetrieveRecipe = didRetrieveRecipe;
    }
}
