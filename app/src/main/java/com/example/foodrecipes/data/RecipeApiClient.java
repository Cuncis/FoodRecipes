package com.example.foodrecipes.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.AppExecutors;
import com.example.foodrecipes.model.Recipe;
import com.example.foodrecipes.model.RecipeResponse;
import com.example.foodrecipes.utils.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.foodrecipes.utils.Constants.NETWORK_TIMEOUT;

public class RecipeApiClient {
    private static final String TAG = "_RecipeApiClient";

    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> recipes;
    private RetrieveRecipesRunnable retrieveRecipesRunnable;

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }

        return instance;
    }

    private RecipeApiClient() {
        recipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }

    public void searchRecipesApi(String query, int pageNumber) {
        if (retrieveRecipesRunnable != null) {
            retrieveRecipesRunnable = null;
        }
        retrieveRecipesRunnable = new RetrieveRecipesRunnable(query, pageNumber);

        final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveRecipesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //  let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveRecipesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveRecipesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getRecipes(query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    List<Recipe> list = new ArrayList<>(((RecipeResponse) response.body()).getRecipes());
                    if (pageNumber == 1) {
                        recipes.postValue(list);
                    } else {
                        List<Recipe> currentRecipes = recipes.getValue();
                        currentRecipes.addAll(list);
                        recipes.postValue(currentRecipes);
                    }
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error );
                    recipes.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private Call<RecipeResponse> getRecipes(String query, int pageNumber) {
            return ApiClient.getRecipeApi().searchRecipe(
                    Constants.API_KEY,
                    query,
                    String.valueOf(pageNumber)
            );
        }

        private void cancelRequqest() {
            Log.d(TAG, "cancelRequqest: canceling the search request");
            cancelRequest = true;
        }

    }

}
























