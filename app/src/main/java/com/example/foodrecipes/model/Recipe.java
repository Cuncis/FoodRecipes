package com.example.foodrecipes.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Recipe{

	@SerializedName("social_rank")
	private double socialRank;

	@SerializedName("f2f_url")
	private String f2fUrl;

	@SerializedName("recipe_id")
	private String recipeId;

	@SerializedName("publisher_url")
	private String publisherUrl;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("publisher")
	private String publisher;

	@SerializedName("ingredients")
	private List<String> ingredients;

	@SerializedName("title")
	private String title;

	@SerializedName("source_url")
	private String sourceUrl;

	public void setSocialRank(double socialRank){
		this.socialRank = socialRank;
	}

	public double getSocialRank(){
		return socialRank;
	}

	public void setF2fUrl(String f2fUrl){
		this.f2fUrl = f2fUrl;
	}

	public String getF2fUrl(){
		return f2fUrl;
	}

	public void setRecipeId(String recipeId){
		this.recipeId = recipeId;
	}

	public String getRecipeId(){
		return recipeId;
	}

	public void setPublisherUrl(String publisherUrl){
		this.publisherUrl = publisherUrl;
	}

	public String getPublisherUrl(){
		return publisherUrl;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setPublisher(String publisher){
		this.publisher = publisher;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setIngredients(List<String> ingredients){
		this.ingredients = ingredients;
	}

	public List<String> getIngredients(){
		return ingredients;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setSourceUrl(String sourceUrl){
		this.sourceUrl = sourceUrl;
	}

	public String getSourceUrl(){
		return sourceUrl;
	}

	@Override
 	public String toString(){
		return 
			"Recipe{" + 
			"social_rank = '" + socialRank + '\'' + 
			",f2f_url = '" + f2fUrl + '\'' + 
			",recipe_id = '" + recipeId + '\'' + 
			",publisher_url = '" + publisherUrl + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",ingredients = '" + ingredients + '\'' + 
			",title = '" + title + '\'' + 
			",source_url = '" + sourceUrl + '\'' + 
			"}";
		}
}