package com.example.mealmate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mealmate.databinding.ActivityAddFridgeBinding;
import com.example.mealmate.databinding.ActivityFridgeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddFridgeActivity extends AppCompatActivity {
    ActivityAddFridgeBinding binding;
    String type = "";
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    DatabaseReference dbRefFridge;
    FridgeModel previousModel;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddFridgeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.app_name));
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        auth = FirebaseAuth.getInstance();
        dbRefFridge = FirebaseDatabase.getInstance().getReference("Fridge");

        if (getIntent().getExtras() != null){
            previousModel = (FridgeModel) getIntent().getSerializableExtra("data");
            setSelection(previousModel.getType());
            binding.etName.setText(previousModel.getName());
            binding.etQuantity.setText(previousModel.getQuantity());
            binding.tvItem.setText("Update/Delete fridge item");
            binding.btnAdd.setText("Update");
            binding.btnDelete.setVisibility(View.VISIBLE);
        }else{
            setSelection("Meat");
        }

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

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (previousModel !=  null){
                    dbRefFridge.child(previousModel.getId()).removeValue();
                    showMessage("Deleted Successfully");
                    finish();
                }
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString();
                String quantity = binding.etQuantity.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(AddFridgeActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (quantity.isEmpty()){
                    Toast.makeText(AddFridgeActivity.this, "Please enter quantity", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (previousModel != null){
                    Map<String, Object> update = new HashMap<>();
                    update.put("type", type);
                    update.put("name", name);
                    update.put("quantity", quantity);
                    dbRefFridge.child(previousModel.getId()).updateChildren(update).addOnCompleteListener(task -> {
                        progressDialog.dismiss();
                        showMessage("Updated Successfully");
                        finish();
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        showMessage(e.getLocalizedMessage());
                    });
                }else{
                    String pushId = dbRefFridge.push().getKey();
                    Date currentDate = Calendar.getInstance().getTime();
                    SimpleDateFormat dateFormat;
                    dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss", Locale.getDefault());
                    String formattedDate = dateFormat.format(currentDate);
                    FridgeModel model = new FridgeModel(pushId, auth.getCurrentUser().getUid(), type, name, formattedDate, quantity);
                    dbRefFridge.child(pushId).setValue(model).addOnCompleteListener(task -> {
                        progressDialog.dismiss();
                        showMessage("Added Successfully");
                        finish();
                    }).addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        showMessage(e.getLocalizedMessage());
                    });
                }
            }
        });
        
    }

    private void setSelection(String category){
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
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
}