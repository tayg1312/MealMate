package com.example.mealmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mealmate.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    private TextView selectedRegimeTextView;
    private TextView selectedPreferenceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvName.setText(HelperClass.users.getName());
        binding.tvEmail.setText(HelperClass.users.getEmail());

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });

        setupRegimeClickListeners();
        setupPreferencesClickListeners();
    }

    private void setupRegimeClickListeners() {
        binding.tvOmnivore.setOnClickListener(regimeClickListener);
        binding.tvVegetarian.setOnClickListener(regimeClickListener);
        binding.tvVegen.setOnClickListener(regimeClickListener);
        binding.tvGlutenFree.setOnClickListener(regimeClickListener);
        binding.tvPorkFree.setOnClickListener(regimeClickListener);
        binding.tvLactoseFree.setOnClickListener(regimeClickListener);
    }

    private void setupPreferencesClickListeners() {
        binding.tvHealthy.setOnClickListener(preferenceClickListener);
        binding.tvWorldCuisine.setOnClickListener(preferenceClickListener);
        binding.tvComfortFood.setOnClickListener(preferenceClickListener);
        binding.tvProteinRich.setOnClickListener(preferenceClickListener);
    }

    private final View.OnClickListener regimeClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedRegimeTextView != null) {
                selectedRegimeTextView.setBackgroundResource(0);
            }
            selectedRegimeTextView = (TextView) v;
            selectedRegimeTextView.setBackgroundResource(R.drawable.bg_selected);
        }
    };

    private final View.OnClickListener preferenceClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectedPreferenceTextView != null) {
                selectedPreferenceTextView.setBackgroundResource(0);
            }
            selectedPreferenceTextView = (TextView) v;
            selectedPreferenceTextView.setBackgroundResource(R.drawable.bg_selected);
        }
    };
}
