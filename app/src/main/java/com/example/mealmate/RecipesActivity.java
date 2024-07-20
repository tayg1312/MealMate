package com.example.mealmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.mealmate.databinding.ActivityDashboardBinding;
import com.example.mealmate.databinding.ActivityRecipesBinding;

import java.util.ArrayList;

public class RecipesActivity extends AppCompatActivity {
    ActivityRecipesBinding binding;
    ArrayList<RecipeModel> recipesList = new ArrayList<>();
    ArrayList<RecipeModel> filteredList = new ArrayList<>();
    RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new RecipesAdapter(recipesList, this);
        binding.rvRecipes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRecipes.setAdapter(adapter);

        setSelection("Beef");

        binding.tvBeef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvBeef.getText().toString());
            }
        });

        binding.tvCarrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvCarrot.getText().toString());
            }
        });

        binding.tvPotato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvPotato.getText().toString());
            }
        });

        binding.tvTomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvTomato.getText().toString());
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterRecipes(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    private void setSelection(String category){
        switch (category) {
            case "Beef":
                binding.tvBeef.setTextColor(getColor(R.color.lightText));
                binding.tvBeef.setBackgroundColor(getColor(R.color.black));
                binding.tvCarrot.setTextColor(getColor(R.color.black));
                binding.tvCarrot.setBackgroundColor(getColor(R.color.lightText));
                binding.tvPotato.setTextColor(getColor(R.color.black));
                binding.tvPotato.setBackgroundColor(getColor(R.color.lightText));
                binding.tvTomato.setTextColor(getColor(R.color.black));
                binding.tvTomato.setBackgroundColor(getColor(R.color.lightText));
                recipesList.clear();
                recipesList.add(new RecipeModel("Beef Stew", "RECIPE | Courtesy of Food Network Kitchen", "3 hours 15 minutes", "286 Reviews", "https://www.youtube.com/watch?v=gyLCCZnOdTk", 5, R.drawable.beef_stew));
                recipesList.add(new RecipeModel("Beef Bourguignon", "RECIPE | Courtesy of Anne Burrell", "16 hours", "114 Reviews", "https://www.youtube.com/watch?v=b6Yqw6J8WHo", 4.5, R.drawable.beef_bourguignon));
                recipesList.add(new RecipeModel("Beef Brisket", "RECIPE | Courtesy of Tyler Florence", "4 hours 35 minutes", "239 Reviews", "https://www.youtube.com/watch?v=VApGfd_2iGQ", 5, R.drawable.beef_bisk));
                adapter.setList(recipesList);
                break;
            case "Carrot":
                binding.tvBeef.setTextColor(getColor(R.color.black));
                binding.tvBeef.setBackgroundColor(getColor(R.color.lightText));
                binding.tvCarrot.setTextColor(getColor(R.color.lightText));
                binding.tvCarrot.setBackgroundColor(getColor(R.color.black));
                binding.tvPotato.setTextColor(getColor(R.color.black));
                binding.tvPotato.setBackgroundColor(getColor(R.color.lightText));
                binding.tvTomato.setTextColor(getColor(R.color.black));
                binding.tvTomato.setBackgroundColor(getColor(R.color.lightText));
                recipesList.clear();
                recipesList.add(new RecipeModel("Carrot Cake", "RECIPE | Courtesy of Natasha's Kitchen", "19 minutes", "66 Reviews", "https://youtu.be/sgpy4uk4bB0", 5, R.drawable.carrot_cake));
                recipesList.add(new RecipeModel("Carrot Ginger Dressing", "RECIPE | Courtesy of Couple Cooks", "16 hours", "218 Reviews", "https://www.youtube.com/watch?v=ZW3bRyMWd68", 4.5, R.drawable.carrot_ginger));
                recipesList.add(new RecipeModel("Carrot Curry", "RECIPE | Courtesy of Swasthi's Recipes", "4 hours 35 minutes", "110 Reviews", "https://www.youtube.com/watch?v=_cQ8zdYfWgI", 5, R.drawable.carrot_curry));
                adapter.setList(recipesList);
                break;
            case "Potato":
                binding.tvBeef.setTextColor(getColor(R.color.black));
                binding.tvBeef.setBackgroundColor(getColor(R.color.lightText));
                binding.tvCarrot.setTextColor(getColor(R.color.black));
                binding.tvCarrot.setBackgroundColor(getColor(R.color.lightText));
                binding.tvPotato.setTextColor(getColor(R.color.lightText));
                binding.tvPotato.setBackgroundColor(getColor(R.color.black));
                binding.tvTomato.setTextColor(getColor(R.color.black));
                binding.tvTomato.setBackgroundColor(getColor(R.color.lightText));
                recipesList.clear();
                recipesList.add(new RecipeModel("Oven-Roasted Potatoes", "RECIPE | Courtesy of Tatyana's Everyday Food", "23 minutes", "88 Reviews", "https://www.youtube.com/watch?v=eCdJSRX9s_0&pp=ygUVT3Zlbi1Sb2FzdGVkIFBvdGF0b2Vz", 5, R.drawable.oven_roasted_potatoes));
                recipesList.add(new RecipeModel("Au Gratin Potatoes", "RECIPE | Courtesy of Natasha's Kitchen", "16 hours", "170 Reviews", "https://www.youtube.com/watch?v=OR44gvBd95A&pp=ygUSQXUgR3JhdGluIFBvdGF0b2Vz", 4.5, R.drawable.au_gratin_potatoes));
                recipesList.add(new RecipeModel("Twice Baked Stuffed Potatoes", "RECIPE | Courtesy of Chef Jean-Pierre", "4 hours 35 minutes", "180 Reviews", "https://www.youtube.com/watch?v=PF856BUb0ic&pp=ygUcVHdpY2UgQmFrZWQgU3R1ZmZlZCBQb3RhdG9lcw%3D%3D", 5, R.drawable.twice_baked_stuffed_potatoes));
                adapter.setList(recipesList);
                break;
            case "Tomato":
                binding.tvBeef.setTextColor(getColor(R.color.black));
                binding.tvBeef.setBackgroundColor(getColor(R.color.lightText));
                binding.tvCarrot.setTextColor(getColor(R.color.black));
                binding.tvCarrot.setBackgroundColor(getColor(R.color.lightText));
                binding.tvPotato.setTextColor(getColor(R.color.black));
                binding.tvPotato.setBackgroundColor(getColor(R.color.lightText));
                binding.tvTomato.setTextColor(getColor(R.color.lightText));
                binding.tvTomato.setBackgroundColor(getColor(R.color.black));
                recipesList.clear();
                recipesList.add(new RecipeModel("Creamy Tomato Soup", "RECIPE | Courtesy of Natasha's Kitchen", "4 minutes", "245 Reviews", "https://www.youtube.com/watch?v=70zs0ZtENho&pp=ygUSQ3JlYW15IFRvbWF0byBTb3Vw", 5, R.drawable.creamy_tomato_soap));
                recipesList.add(new RecipeModel("Oven-Roasted Tomatoes", "RECIPE | Courtesy of Mediterranean Dish", "8 hours", "114 Reviews", "https://www.youtube.com/watch?v=WZ9YsHxxC_0&pp=ygUVT3Zlbi1Sb2FzdGVkIFRvbWF0b2Vz", 4.5, R.drawable.oven_roasted_tomatoes));
                recipesList.add(new RecipeModel("Easy Tomato Soup", "RECIPE | Courtesy of Natasha's Kitchen", "2 hours 53 minutes", "107 Reviews", "https://www.youtube.com/watch?v=szjZ3vqwyXE&pp=ygUQRWFzeSBUb21hdG8gU291cA%3D%3D", 5, R.drawable.easy_tomato_soup));
                adapter.setList(recipesList);
                break;
        }
    }

    private void filterRecipes(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(recipesList);
        } else {
            for (RecipeModel recipe : recipesList) {
                if (recipe.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(recipe);
                }
            }
        }
        adapter.setList(filteredList);
    }

}