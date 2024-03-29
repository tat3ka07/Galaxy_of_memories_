package com.example.galaxyofmemories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

public class MainActivity extends AppCompatActivity {
    CardView clothingCard;
    private View birthdayBtn;
    private View shoppingBtn;
    private View educationBtn;
    private View familyBtn;
    private View personalBtn;
    private View travellingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clothingCard = findViewById(R.id.clothingCard);

        birthdayBtn = findViewById(R.id.birthdayBtn);
        birthdayBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Birthday.class);
            startActivity(intent);
        });

        educationBtn = findViewById(R.id.educationBtn);
        educationBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Education.class);
            startActivity(intent);
        });

        shoppingBtn = findViewById(R.id.shoppingBtn);
        shoppingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Shopping.class);
            startActivity(intent);
        });

        familyBtn = findViewById(R.id.familyBtn);
        familyBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Family.class);
            startActivity(intent);
        });

        personalBtn = findViewById(R.id.personalBtn);
        personalBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Personal.class);
            startActivity(intent);
        });

        travellingBtn = findViewById(R.id.travellingBtn);
        travellingBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Travelling.class);
            startActivity(intent);
        });
            }
}


