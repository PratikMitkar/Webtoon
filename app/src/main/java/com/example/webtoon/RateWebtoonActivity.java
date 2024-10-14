package com.example.webtoon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RateWebtoonActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView averageRatingTextView;
    private Button submitButton;
    private String title;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_webtoon);

        ratingBar = findViewById(R.id.ratingBar);
        averageRatingTextView = findViewById(R.id.averageRatingTextView);
        submitButton = findViewById(R.id.submitButton);

        // Get the title of the webtoon from the intent
        Intent intent = getIntent();
        title = intent.getStringExtra("title");

        // Initialize SharedPreferences for storing ratings
        sharedPreferences = getSharedPreferences("WebtoonRatings", MODE_PRIVATE);
        loadAverageRating(); // Load existing average rating

        // Set a listener for the submit button
        submitButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            saveRating(rating); // Save the new rating
            finish(); // Close the activity
        });
    }

    private void saveRating(float rating) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(title, rating); // Save rating by title
        editor.apply();
    }

    private void loadAverageRating() {
        float totalRating = 0;
        int count = 0;

        // Calculate the average rating for the current webtoon
        for (String key : sharedPreferences.getAll().keySet()) {
            if (key.equals(title)) {
                totalRating += sharedPreferences.getFloat(key, 0);
                count++;
            }
        }

        if (count > 0) {
            float averageRating = totalRating / count;
            averageRatingTextView.setText(String.format("Average Rating: %.1f", averageRating)); // Display average rating
            ratingBar.setRating(averageRating); // Set the rating bar to the average rating
        } else {
            averageRatingTextView.setText("Average Rating: 0"); // Default if no ratings
        }
    }
}
