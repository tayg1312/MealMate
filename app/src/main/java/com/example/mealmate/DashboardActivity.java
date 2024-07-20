package com.example.mealmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mealmate.databinding.ActivityDashboardBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, RecipesActivity.class));
            }
        });

        binding.tvFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, FridgeActivity.class));
            }
        });

        binding.tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
            }
        });

        setPieChartData(binding.lipidsPieChart, 32, 73, "32/73 g");
        setPieChartData(binding.proteinsPieChart, 87, 95, "87/95 g");
        setPieChartData(binding.carbsPieChart, 125, 278, "125/278 g");
        setPieChartData(binding.fibersPieChart, 22, 27, "22/27 g");
    }

    private void setPieChartData(PieChart pieChart, float value, float total, String centerText) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(value));
        entries.add(new PieEntry(total - value));

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setDrawValues(false);

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(85f);
        pieChart.setHoleColor(getResources().getColor(R.color.light));
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterText(centerText);
        pieChart.setCenterTextSize(10f);
        pieChart.animateY(1000);
        pieChart.invalidate();
    }
}
