package com.example.foodrecipes.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Recipes implements Parcelable {

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

	@SerializedName("title")
	private String title;

	@SerializedName("source_url")
	private String sourceUrl;

	public Recipes() {
	}

	protected Recipes(Parcel in) {
		socialRank = in.readDouble();
		f2fUrl = in.readString();
		recipeId = in.readString();
		publisherUrl = in.readString();
		imageUrl = in.readString();
		publisher = in.readString();
		title = in.readString();
		sourceUrl = in.readString();
	}

	public static final Creator<Recipes> CREATOR = new Creator<Recipes>() {
		@Override
		public Recipes createFromParcel(Parcel in) {
			return new Recipes(in);
		}

		@Override
		public Recipes[] newArray(int size) {
			return new Recipes[size];
		}
	};

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
			"Recipes{" +
			"social_rank = '" + socialRank + '\'' + 
			",f2f_url = '" + f2fUrl + '\'' + 
			",recipe_id = '" + recipeId + '\'' + 
			",publisher_url = '" + publisherUrl + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",publisher = '" + publisher + '\'' + 
			",title = '" + title + '\'' + 
			",source_url = '" + sourceUrl + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeDouble(socialRank);
		parcel.writeString(f2fUrl);
		parcel.writeString(recipeId);
		parcel.writeString(publisherUrl);
		parcel.writeString(imageUrl);
		parcel.writeString(publisher);
		parcel.writeString(title);
		parcel.writeString(sourceUrl);
	}
}