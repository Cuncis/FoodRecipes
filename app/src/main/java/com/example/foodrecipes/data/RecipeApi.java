package com.example.foodrecipes.data;

import com.example.foodrecipes.model.RecipeResponse;
import com.example.foodrecipes.model.RecipesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeApi {


    // SEARCH
//    @GET("api/search")
//    Call<RecipesResponse> searchRecipe(@Query("key") String apiKey,
//                                       @Query("q") String query,
//                                       @Query("page") String page);
//
//    @GET("api/get")
//    Call<RecipeResponse> getRecipe(@Query("key") String key,
//                                   @Query("rId") String recipeId);

    // SEARCH
    @GET("api/search")
    Call<RecipesResponse> searchRecipe(@Query("q") String query,
                                       @Query("page") String page);

    @GET("api/get")
    Call<RecipeResponse> getRecipe(@Query("rId") String recipeId);

}
