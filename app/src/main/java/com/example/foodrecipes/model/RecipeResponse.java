package com.example.foodrecipes.model;

import com.google.gson.annotations.SerializedName;

public class RecipeResponse{

	@SerializedName("recipe")
	private Recipe recipe;

	public void setRecipe(Recipe recipe){
		this.recipe = recipe;
	}

	public Recipe getRecipe(){
		return recipe;
	}

	@Override
 	public String toString(){
		return 
			"RecipeResponse{" + 
			"recipe = '" + recipe + '\'' + 
			"}";
		}
}