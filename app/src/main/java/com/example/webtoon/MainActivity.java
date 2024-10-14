package com.example.webtoon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webtoon.FavoritesActivity;
import com.example.webtoon.R;
import com.example.webtoon.Webtoon;
import com.example.webtoon.WebtoonAdapter;
import com.example.webtoon.WebtoonViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WebtoonAdapter adapter;
    private WebtoonViewModel viewModel;
    private FloatingActionButton fabFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new WebtoonAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(WebtoonViewModel.class);
        viewModel.getWebtoons().observe(this, new Observer<List<Webtoon>>() {
            @Override
            public void onChanged(List<Webtoon> webtoons) {
                adapter.updateData(webtoons); // Update the RecyclerView adapter with data
            }
        });

        // Initialize and set up the Floating Action Button
        fabFavorites = findViewById(R.id.fab_favorites);
        fabFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent); // Navigate to FavoritesActivity
            }
        });
    }
}
