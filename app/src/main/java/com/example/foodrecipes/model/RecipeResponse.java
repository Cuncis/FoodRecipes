package com.example.foodrecipes.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class RecipeResponse{

	@SerializedName("recipes")
	private List<Recipe> recipes;

	@SerializedName("count")
	private int count;

	public void setRecipes(List<Recipe> recipes){
		this.recipes = recipes;
	}

	public List<Recipe> getRecipes(){
		return recipes;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	@Override
 	public String toString(){
		return 
			"RecipeResponse{" + 
			"recipes = '" + recipes + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}