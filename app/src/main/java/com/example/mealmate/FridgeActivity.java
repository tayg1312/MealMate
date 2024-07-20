package com.example.mealmate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mealmate.databinding.ActivityFridgeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class FridgeActivity extends AppCompatActivity {
    ActivityFridgeBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    DatabaseReference dbRefFridge;
    ArrayList<FridgeModel> fridgeList = new ArrayList<>();
    ArrayList<FridgeModel> finalList = new ArrayList<>();
    ArrayList<FridgeModel> filteredList = new ArrayList<>();
    FridgeAdapter adapter;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFridgeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        dbRefFridge = FirebaseDatabase.getInstance().getReference("Fridge");

        binding.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FridgeActivity.this, AddFridgeActivity.class));
            }
        });

        adapter = new FridgeAdapter(finalList, this);
        binding.rvRecipes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRecipes.setAdapter(adapter);

        setSelection("Meat");

        binding.tvMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvMeat.getText().toString());
            }
        });

        binding.tvVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvVegetables.getText().toString());
            }
        });

        binding.tvFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvFruits.getText().toString());
            }
        });

        binding.tvOils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(binding.tvOils.getText().toString());
            }
        });

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterItems(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog.show();
        dbRefFridge.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    fridgeList.clear();
                    progressDialog.dismiss();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        try {
                            FridgeModel model = ds.getValue(FridgeModel.class);
                            if (model.getUserId().equals(auth.getCurrentUser().getUid())) {
                                fridgeList.add(model);
                            }
                        } catch (DatabaseException e) {
                            e.printStackTrace();
                        }
                    }

                    setSelection("Meat");
                } else {
                    progressDialog.dismiss();
                    binding.tvNoDataFound.setVisibility(View.VISIBLE);
                    binding.rvRecipes.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(FridgeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setSelection(String category) {
        type = category;
        switch (category) {
            case "Meat":
                binding.tvMeat.setTextColor(getColor(R.color.lightText));
                binding.tvMeat.setBackgroundColor(getColor(R.color.black));
                binding.tvVegetables.setTextColor(getColor(R.color.black));
                binding.tvVegetables.setBackgroundColor(getColor(R.color.lightText));
                binding.tvFruits.setTextColor(getColor(R.color.black));
                binding.tvFruits.setBackgroundColor(getColor(R.color.lightText));
                binding.tvOils.setTextColor(getColor(R.color.black));
                binding.tvOils.setBackgroundColor(getColor(R.color.lightText));
                break;
            case "Vegetables":
                binding.tvMeat.setTextColor(getColor(R.color.black));
                binding.tvMeat.setBackgroundColor(getColor(R.color.lightText));
                binding.tvVegetables.setTextColor(getColor(R.color.lightText));
                binding.tvVegetables.setBackgroundColor(getColor(R.color.black));
                binding.tvFruits.setTextColor(getColor(R.color.black));
                binding.tvFruits.setBackgroundColor(getColor(R.color.lightText));
                binding.tvOils.setTextColor(getColor(R.color.black));
                binding.tvOils.setBackgroundColor(getColor(R.color.lightText));

                break;
            case "Fruits":
                binding.tvMeat.setTextColor(getColor(R.color.black));
                binding.tvMeat.setBackgroundColor(getColor(R.color.lightText));
                binding.tvVegetables.setTextColor(getColor(R.color.black));
                binding.tvVegetables.setBackgroundColor(getColor(R.color.lightText));
                binding.tvFruits.setTextColor(getColor(R.color.lightText));
                binding.tvFruits.setBackgroundColor(getColor(R.color.black));
                binding.tvOils.setTextColor(getColor(R.color.black));
                binding.tvOils.setBackgroundColor(getColor(R.color.lightText));

                break;
            case "Oils":
                binding.tvMeat.setTextColor(getColor(R.color.black));
                binding.tvMeat.setBackgroundColor(getColor(R.color.lightText));
                binding.tvVegetables.setTextColor(getColor(R.color.black));
                binding.tvVegetables.setBackgroundColor(getColor(R.color.lightText));
                binding.tvFruits.setTextColor(getColor(R.color.black));
                binding.tvFruits.setBackgroundColor(getColor(R.color.lightText));
                binding.tvOils.setTextColor(getColor(R.color.lightText));
                binding.tvOils.setBackgroundColor(getColor(R.color.black));

                break;
        }
        finalList.clear();
        if (!fridgeList.isEmpty()) {
            for (FridgeModel fridgeModel : fridgeList) {
                if (Objects.equals(fridgeModel.getType(), type)) {
                    finalList.add(fridgeModel);
                }
            }
            if (finalList.isEmpty()) {
                binding.tvNoDataFound.setVisibility(View.VISIBLE);
                binding.rvRecipes.setVisibility(View.GONE);
            } else {
                adapter.setList(finalList);
                binding.tvNoDataFound.setVisibility(View.GONE);
                binding.rvRecipes.setVisibility(View.VISIBLE);
            }
        }
    }

    private void filterItems(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(finalList);
        } else {
            for (FridgeModel recipe : finalList) {
                if (recipe.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(recipe);
                }
            }
        }
        adapter.setList(filteredList);
    }

}