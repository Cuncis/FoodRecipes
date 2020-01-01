package com.example.foodrecipes.utils;

import retrofit2.http.PUT;

public class Constants {

//    public static final String BASE_URL = "https://www.food2fork.com/";
    public static final String BASE_URL = "http://recipesapi.herokuapp.com/";
    public static final int NETWORK_TIMEOUT = 3000;

//    public static final String API_KEY = "a659409e551d6af128a9f572a31a3e95";
    public static final String API_KEY = "";
//    public static final String API_KEY = "2f692dc307c603d1d8f3a7dbd4f3a7b2 ";

    public static final String[] DEFAULT_SEARCH_CATEGORIES =
            {"Barbeque", "Breakfast", "Chicken", "Beef", "Brunch", "Dinner", "Wine", "Italian"};

    public static final String[] DEFAULT_SEARCH_CATEGORY_IMAGES =
            {
                    "barbeque",
                    "breakfast",
                    "chicken",
                    "beef",
                    "brunch",
                    "dinner",
                    "wine",
                    "italian"
            };

}
