package com.example.webtoon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WebtoonAdapter webtoonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load webtoons from JSON
        List<Webtoon> webtoonList = Utils.loadWebtoons(this);

        // Set up Adapter
        if (webtoonList != null) {
            webtoonAdapter = new WebtoonAdapter(this, webtoonList);
            recyclerView.setAdapter(webtoonAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.d("MainActivity", "Clicked item ID: " + item.getItemId());  // Debug log

        // Use if instead of switch for debugging
        if (item.getItemId() == R.id.action_favorites) {
            // Open the FavoritesActivity
            startActivity(new Intent(this, FavoritesActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
