package com.example.galaxyofmemories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    CardView clothingCard;
    private View birthdayBnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clothingCard = findViewById(R.id.clothingCard);

        birthdayBnt = findViewById(R.id.birthdayBtn);
        birthdayBnt.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNote.class);
            startActivity(intent);
        });

    }
}