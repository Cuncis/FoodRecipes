package com.example.foodrecipes.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class RecipesResponse {

	@SerializedName("recipes")
	private List<Recipes> recipes;

	@SerializedName("count")
	private int count;

	public void setRecipes(List<Recipes> recipes){
		this.recipes = recipes;
	}

	public List<Recipes> getRecipes(){
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
			"RecipesResponse{" +
			"recipes = '" + recipes + '\'' + 
			",count = '" + count + '\'' + 
			"}";
		}
}