package com.example.recipeapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recipeapp.R;
import com.example.recipeapp.model.DetailResult;
import com.example.recipeapp.model.RecipeResult;
import com.example.recipeapp.view.CustomOnItemClickListener;
import com.example.recipeapp.view.DetailActivity;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Activity activity;
    private ArrayList<RecipeResult> arrayList;
    private String baseUri;
    public static final String BASE_URI = "https://spoonacular.com/recipeImages/";

    public RecipeAdapter(Activity activity, String baseUri, ArrayList<RecipeResult> arrayList) {
        this.activity = activity;
        this.baseUri = baseUri;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_list_recipe, parent, false);
        return new RecipeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {
        RecipeResult result = arrayList.get(position);

        String url_poster = BASE_URI + result.getImage();
        Glide.with(activity).load(url_poster).into(holder.ivPoster);

        holder.tvTitle.setText(result.getTitle());
        holder.tvReadyCook.setText("Ready to be feasted in : "+result.getReadyCook()+" minutes");


        holder.cvListRecipe.setOnClickListener(new CustomOnItemClickListener(position,(view, position1) -> {
            Intent intent=new Intent(activity, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_ARTICLE, result);
            activity.startActivity(intent);
        }));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        CardView cvListRecipe;
        TextView tvTitle, tvReadyCook;
        ImageView ivPoster;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            cvListRecipe = itemView.findViewById(R.id.cv_list_recipe);
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivPoster = itemView.findViewById(R.id.iv_recipe);
            tvReadyCook=itemView.findViewById(R.id.tv_ready_in_minutes);

        }
    }
}
