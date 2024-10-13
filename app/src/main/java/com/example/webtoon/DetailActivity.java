package com.example.webtoon;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private LinearLayout detailLayout; // Layout to dynamically add multiple views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Find layout to dynamically add content
        detailLayout = findViewById(R.id.detailLayout);

        // Get data from intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String writer = intent.getStringExtra("writer");
        String artist = intent.getStringExtra("artist");
        String reads = intent.getStringExtra("reads");
        List<String> images = (List<String>) intent.getSerializableExtra("images");
        List<String> subtitles = (List<String>) intent.getSerializableExtra("subtitles");
        List<String> descriptions = (List<String>) intent.getSerializableExtra("descriptions");

        // Add title
        TextView animeTitle = new TextView(this);
        animeTitle.setText(title);
        animeTitle.setTextSize(24);
        animeTitle.setTypeface(null, android.graphics.Typeface.BOLD);
        detailLayout.addView(animeTitle);

        // Add writer and artist
        TextView animeDetails = new TextView(this);
        animeDetails.setText("Written by: " + writer + "\nArtist: " + artist);
        animeDetails.setTextSize(18);
        detailLayout.addView(animeDetails);

        // Add reads
        TextView animeReads = new TextView(this);
        animeReads.setText("Reads: " + reads);
        animeReads.setTextSize(18);
        detailLayout.addView(animeReads);

        // Dynamically add images, subtitles, and descriptions
        for (int i = 0; i < images.size(); i++) {
            // Add image
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(images.get(i)).into(imageView);
            detailLayout.addView(imageView);

            // Add subtitle
            TextView subtitleView = new TextView(this);
            subtitleView.setText(subtitles.get(i));
            subtitleView.setTextSize(20);
            subtitleView.setTypeface(null, android.graphics.Typeface.BOLD);
            detailLayout.addView(subtitleView);

            // Add description
            TextView descriptionView = new TextView(this);
            descriptionView.setText(descriptions.get(i));
            descriptionView.setTextSize(16);
            detailLayout.addView(descriptionView);
        }
    }
}
