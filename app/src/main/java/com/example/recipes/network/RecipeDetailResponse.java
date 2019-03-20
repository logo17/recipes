package com.example.recipes.network;

import com.google.gson.annotations.SerializedName;

public class RecipeDetailResponse {
    @SerializedName("id")
    private int recipeId;
    @SerializedName("title")
    private String title;
    @SerializedName("rating")
    private float rating;
    @SerializedName("image")
    private String image;
    @SerializedName("instructions")
    private String instructions;

    public int getRecipeId() {
        return recipeId;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getImage() {
        return image;
    }

    public String getInstructions() {
        return instructions;
    }
}
