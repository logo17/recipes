package com.example.recipes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipes.ClickListener;
import com.example.recipes.R;
import com.example.recipes.network.RecipeResponse;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder> {

    private ClickListener clickListener;

    public RecipesAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setRecipeResponseList(List<RecipeResponse> recipeResponseList) {
        this.recipeResponseList = recipeResponseList;
        notifyDataSetChanged();
    }

    private List<RecipeResponse> recipeResponseList;

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recipe_cell, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bindView(recipeResponseList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        int result = 0;
        if (recipeResponseList != null && recipeResponseList.size() > 0) {
            result = recipeResponseList.size();
        }
        return result;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recipeTitleTextView)
        TextView recipeTitle;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onCellClicked(recipeResponseList.get(getAdapterPosition()).getRecipeId());
                }
            });
            ButterKnife.bind(this, itemView);
        }

        void bindView(String title) {
            recipeTitle.setText(title);
        }
    }

}
