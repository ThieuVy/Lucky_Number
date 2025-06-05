package com.example.bt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random; // Added import

public class RandomActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random);

        TextView title = findViewById(R.id.title_2);
        TextView luckyNumber = findViewById(R.id.lucky_number);
        Button btnSave = findViewById(R.id.btn_Save);

        // Get the user's name from the intent
        String userName = getIntent().getStringExtra("USER_NAME");
        if (userName != null) {
            title.setText(userName + "'s Lucky Number is:");
        }

        // Generate and display random number
        int randomNum = new Random().nextInt(1000);
        luckyNumber.setText(String.valueOf(randomNum));

        // Save the lucky number and return to MainActivity
        btnSave.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("LuckyNumberPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("lucky_number", randomNum);
            editor.putString("user_name", userName);
            editor.apply();

            Intent intent = new Intent(RandomActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}