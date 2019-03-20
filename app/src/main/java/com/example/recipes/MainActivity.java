package com.example.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.recipes.adapters.RecipesAdapter;
import com.example.recipes.network.RecipeResponse;
import com.example.recipes.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ClickListener {

    public static final String RECIPE_ID = "RECIPE_ID";
    @BindView(R.id.recipesRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.serchView)
    SearchView searchView;
    @BindView(R.id.emptyLabel)
    TextView emptyLabel;

    private List<RecipeResponse> recipeResponseList;
    private RecipesAdapter recipesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recipesAdapter = new RecipesAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recipesAdapter);
        final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getRecipes().observe(this, new Observer<List<RecipeResponse>>() {
            @Override
            public void onChanged(@Nullable List<RecipeResponse> recipeResponses) {
                recipeResponseList = recipeResponses;
                handleListResults(recipeResponses);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText.isEmpty()) {
                    handleListResults(recipeResponseList);
                } else {
                    handleListResults(findRecipes(newText));
                }
                return true;
            }
        });

    }


    private List<RecipeResponse> findRecipes(String searchText) {
        List<RecipeResponse> result = new ArrayList<>();
        for (RecipeResponse recipe : recipeResponseList) {
            if (recipe.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                result.add(recipe);
            }
        }
        return result;
    }

    private void handleListResults(List<RecipeResponse> recipeResponses) {
        if (recipeResponses != null && recipeResponses.size() > 0) {
            recipesAdapter.setRecipeResponseList(recipeResponses);
            emptyLabel.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyLabel.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onCellClicked(int recipeId) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RECIPE_ID, recipeId);
        startActivity(intent);
    }
}
