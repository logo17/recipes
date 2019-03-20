package com.example.recipes;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipes.adapters.RecipesAdapter;
import com.example.recipes.network.RecipeDetailResponse;
import com.example.recipes.network.RecipeResponse;
import com.example.recipes.viewmodels.DetailViewModel;
import com.example.recipes.viewmodels.MainViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.recipes.MainActivity.RECIPE_ID;

public class RecipeDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recipeImage)
    ImageView recipeImage;
    @BindView(R.id.recipeRate)
    AppCompatRatingBar recipeRate;
    @BindView(R.id.recipeDescription)
    TextView recipeDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final DetailViewModel viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        int recipeId = getIntent().getIntExtra(RECIPE_ID, -1);
        viewModel.getRecipeDetail(recipeId).observe(this, new Observer<RecipeDetailResponse>() {
            @Override
            public void onChanged(RecipeDetailResponse recipeDetailResponse) {
                getSupportActionBar().setTitle(recipeDetailResponse.getTitle());
                recipeDescription.setText(recipeDetailResponse.getInstructions());
                Picasso.get()
                        .load(recipeDetailResponse.getImage())
                        .fit()
                        .into(recipeImage);
                recipeRate.setRating(recipeDetailResponse.getRating());
            }
        });
    }



}
