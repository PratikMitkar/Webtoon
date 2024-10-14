package com.example.webtoon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView webtoonTitle, webtoonDetails;
    private LinearLayout dynamicContentContainer;
    private FloatingActionButton rateButton; // Changed from Button to FloatingActionButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize views
        webtoonTitle = findViewById(R.id.webtoonTitle);
        webtoonDetails = findViewById(R.id.webtoonDetails);
        dynamicContentContainer = findViewById(R.id.dynamicContentContainer);
        rateButton = findViewById(R.id.rateButton); // Cast to FloatingActionButton

        // Get data from the intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String writer = intent.getStringExtra("writer");
        String artist = intent.getStringExtra("artist");
        String reads = intent.getStringExtra("reads");
        List<String> descriptions = (List<String>) intent.getSerializableExtra("descriptions");
        List<String> subtitles = (List<String>) intent.getSerializableExtra("subtitles");
        List<String> images = (List<String>) intent.getSerializableExtra("images");

        // Set title and details
        webtoonTitle.setText(title);
        webtoonDetails.setText("Written by: " + writer + "\nArtist: " + artist + "\nReads: " + reads);

        // Dynamically add Image → Subtitle → Description format
        for (int i = 0; i < images.size(); i++) {
            // Add Image
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(images.get(i)).into(imageView);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 400);
            imageParams.setMargins(0, 16, 0, 16);
            imageView.setLayoutParams(imageParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            dynamicContentContainer.addView(imageView);

            // Add Subtitle (if available)
            if (i < subtitles.size()) {
                TextView subtitleView = new TextView(this);
                subtitleView.setText(subtitles.get(i));
                subtitleView.setTextSize(20);
                subtitleView.setTypeface(null, android.graphics.Typeface.BOLD);
                LinearLayout.LayoutParams subtitleLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                subtitleLayoutParams.setMargins(0, 16, 0, 8);
                subtitleView.setLayoutParams(subtitleLayoutParams);
                dynamicContentContainer.addView(subtitleView);
            }

            // Add Description (if available)
            if (i < descriptions.size()) {
                TextView descriptionView = new TextView(this);
                descriptionView.setText(descriptions.get(i));
                descriptionView.setTextSize(16);
                LinearLayout.LayoutParams descriptionLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                descriptionLayoutParams.setMargins(0, 0, 0, 16);
                descriptionView.setLayoutParams(descriptionLayoutParams);
                dynamicContentContainer.addView(descriptionView);
            }
        }

        // Set up the Floating Action Button to open the RateWebtoonActivity
        rateButton.setOnClickListener(v -> {
            Intent rateIntent = new Intent(DetailActivity.this, RateWebtoonActivity.class);
            rateIntent.putExtra("title", title); // Pass the webtoon title to the Rate activity
            startActivity(rateIntent);
        });
    }
}
