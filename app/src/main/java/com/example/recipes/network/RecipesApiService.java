package com.example.recipes.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipesApiService {

//    https://gl-endpoint.herokuapp.com/recipes
    @GET("recipes")
    Call<List<RecipeResponse>> getRecipes();

    @GET("recipes/{id}")
    Call<RecipeDetailResponse> getRecipeDetail(@Path("id") int recipeId);
}
