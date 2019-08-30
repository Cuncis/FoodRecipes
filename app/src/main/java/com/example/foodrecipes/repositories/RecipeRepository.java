package com.example.foodrecipes.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.model.Recipe;

import java.util.List;

public class RecipeRepository {

    public static RecipeRepository instance;
    private MutableLiveData<List<Recipe>> recipes;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }

        return instance;
    }

    private RecipeRepository() {
        recipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

}
