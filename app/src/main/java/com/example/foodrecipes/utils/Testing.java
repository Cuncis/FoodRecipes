package com.example.foodrecipes.utils;

import android.util.Log;

import com.example.foodrecipes.model.Recipes;

import java.util.List;

public class Testing {
    public static void printRecipes(List<Recipes> list, String tag) {
        for (Recipes recipes : list) {
            Log.d(tag, "printRecipes: " + recipes.getTitle());
        }
    }
}
