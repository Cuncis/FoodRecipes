package com.example.foodrecipes.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.foodrecipes.data.RecipeApiClient;
import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.model.Recipes;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeApiClient recipeApiClient;
    private String query;
    private int pageNumber;
    private MutableLiveData<Boolean> isQueryExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<Recipes>> recipes = new MediatorLiveData<>();

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }

        return instance;
    }

    private RecipeRepository() {
        recipeApiClient = RecipeApiClient.getInstance();
        initMediators();
    }

    private void initMediators() {
        LiveData<List<Recipes>> recipeListDataSource = recipeApiClient.getRecipes();
        recipes.addSource(recipeListDataSource, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(List<Recipes> recipeList) {
                if (recipeList != null) {
                    recipes.setValue(recipeList);
                    doneQuery(recipeList);
                } else {
                    // Search database cache
                    doneQuery(null);
                }
            }
        });
    }

    private void doneQuery(List<Recipes> list) {
        if (list != null) {
            if (list.size() % 30 != 0) {
                isQueryExhausted.setValue(true);
            }
        } else {
            isQueryExhausted.setValue(true);
        }
    }

    public LiveData<Boolean> isQueryExhausted() {
        return isQueryExhausted;
    }

//    public LiveData<List<Recipes>> getRecipes() {
//        return recipeApiClient.getRecipes();
//    }

    public LiveData<List<Recipes>> getRecipes() {
        return recipes;
    }

    public LiveData<Recipe> getRecipe() {
        return recipeApiClient.getRecipe();
    }

    public LiveData<Boolean> isRecipeTimedOut() {
        return recipeApiClient.isRecipeTimedOut();
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
        isQueryExhausted.setValue(false);
        recipeApiClient.searchRecipesApi(query, pageNumber);
    }

    public void searchNextPage() {
        searchRecipesApi(query, pageNumber + 1);
    }

    public void cancelRequest(){
        recipeApiClient.cancelRequest();
    }

}
