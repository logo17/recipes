package com.example.recipes.network;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeApiImpl {
    private static RecipeApiImpl instance;

    public static synchronized RecipeApiImpl getInstance() {
        if(instance == null){
            instance = new RecipeApiImpl();
        }
        return instance;
    }

    public LiveData<List<RecipeResponse>> getRecipes() {
        final MutableLiveData<List<RecipeResponse>> data = new MutableLiveData<>();
        RetrofitProvider.getInstance().getApiService().getRecipes().enqueue(new Callback<List<RecipeResponse>>() {
            @Override
            public void onResponse(Call<List<RecipeResponse>> call, Response<List<RecipeResponse>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<RecipeResponse>> call, Throwable t) {

            }
        });
        return data;
    }

    public LiveData<RecipeDetailResponse> getRecipeDetail(int recipeId) {
        final MutableLiveData<RecipeDetailResponse> data = new MutableLiveData<>();
        RetrofitProvider.getInstance().getApiService().getRecipeDetail(recipeId).enqueue(new Callback<RecipeDetailResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailResponse> call, Response<RecipeDetailResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeDetailResponse> call, Throwable t) {

            }
        });
        return data;
    }
}
