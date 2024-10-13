package com.example.webtoon;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;  // Use RecyclerView
    private WebtoonAdapter webtoonAdapter;
    private SharedPreferences sharedPreferences;
    private List<Webtoon> favoriteWebtoons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);

        // Initialize the list of favorite webtoons
        favoriteWebtoons = new ArrayList<>();

        // Get the list of favorite webtoons from SharedPreferences
        for (String key : sharedPreferences.getAll().keySet()) {
            Webtoon webtoon = Utils.getWebtoonByTitle(this, key);  // Fetch Webtoon object by title
            if (webtoon != null) {
                favoriteWebtoons.add(webtoon);
            }
        }

        // Set up the adapter for RecyclerView
        webtoonAdapter = new WebtoonAdapter(this, favoriteWebtoons);
        recyclerView.setAdapter(webtoonAdapter);
    }
}
