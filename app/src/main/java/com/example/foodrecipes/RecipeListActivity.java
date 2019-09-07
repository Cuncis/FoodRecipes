package com.example.foodrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipes.adapter.OnRecipeListener;
import com.example.foodrecipes.adapter.RecipeAdapter;
import com.example.foodrecipes.model.Recipes;
import com.example.foodrecipes.utils.Testing;
import com.example.foodrecipes.utils.VerticalSpacingItemDecorator;
import com.example.foodrecipes.viewmodel.RecipeListViewModel;

import java.util.List;

public class RecipeListActivity extends BaseActivity implements OnRecipeListener {
    private static final String TAG = "_RecipeListActivity";

    private RecyclerView rvRecipes;
    private SearchView searchView;

    private RecipeListViewModel recipeListViewModel;
    private RecipeAdapter recipeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);
        rvRecipes = findViewById(R.id.rv_recipes);
        searchView = findViewById(R.id.search_view);

        recipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if (!recipeListViewModel.isViewingRecipes()) {
            // Display Search Category
            displaySearchCategory();
        }

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    private void subscribeObservers() {
        recipeListViewModel.getRecipes().observe(this, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(List<Recipes> recipes) {
                if (recipes != null) {
                    if (recipeListViewModel.isViewingRecipes()) {
                        Testing.printRecipes(recipes, TAG);
                        recipeListViewModel.setIsPerformingQuery(false);
                        recipeAdapter.setRecipes(recipes);
                    }
                }
            }
        });

        recipeListViewModel.isQueryExhausted().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Log.d(TAG, "onChanged: The query is exhausted...");
                    recipeAdapter.setQueryExhausted();
                }
            }
        });
    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                recipeAdapter.displayLoading();
                recipeListViewModel.searchRecipesApi(s, 1);
                searchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initRecyclerView() {
        recipeAdapter = new RecipeAdapter(this);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        rvRecipes.addItemDecoration(itemDecorator);
        rvRecipes.setAdapter(recipeAdapter);
        rvRecipes.setLayoutManager(new LinearLayoutManager(this));

        rvRecipes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!rvRecipes.canScrollVertically(1)) {
                    // Search the next page
                    recipeListViewModel.searchNextPage();
                }
            }
        });
    }

    @Override
    public void onRecipeClick(int position) {
        Intent i = new Intent(this, RecipeActivity.class);
        i.putExtra("recipe", recipeAdapter.getSelectedRecipe(position));
        startActivity(i);
    }

    @Override
    public void onCategoryClick(String category) {
        recipeAdapter.displayLoading();
        recipeListViewModel.searchRecipesApi(category, 1);
        searchView.clearFocus();
    }

    private void displaySearchCategory() {
        recipeListViewModel.setIsViewingrecipes(false);
        recipeAdapter.displaySearchCategories();
    }

    @Override
    public void onBackPressed() {
        if (recipeListViewModel.onBackPressed()) {
            super.onBackPressed();
        } else {
            displaySearchCategory();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_category) {
            displaySearchCategory();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}






















