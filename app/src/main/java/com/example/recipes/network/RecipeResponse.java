package com.example.recipes.network;

import com.google.gson.annotations.SerializedName;

public class RecipeResponse {
    @SerializedName("id")
    private int recipeId;
    @SerializedName("title")
    private String title;

    public int getRecipeId() {
        return recipeId;
    }

    public String getTitle() {
        return title;
    }
}
