package com.example.recipes.viewmodels;

import com.example.recipes.network.RecipeApiImpl;
import com.example.recipes.network.RecipeDetailResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {

    public LiveData<RecipeDetailResponse> getRecipeDetail(int recipeId){
        return RecipeApiImpl.getInstance().getRecipeDetail(recipeId);
    }

}
