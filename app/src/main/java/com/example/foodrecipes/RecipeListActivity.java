package com.example.foodrecipes;

import android.os.Bundle;
import android.view.View;

public class RecipeListActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recipe);

        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressBar.getVisibility() == View.VISIBLE) {
                    showProgressBar(false);
                } else {
                    showProgressBar(true);
                }
            }
        });

        // test
    }

}