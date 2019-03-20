package com.example.recipes.viewmodels;

import com.example.recipes.network.RecipeApiImpl;
import com.example.recipes.network.RecipeResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public LiveData<List<RecipeResponse>> getRecipes(){
        return RecipeApiImpl.getInstance().getRecipes();
    }

}
