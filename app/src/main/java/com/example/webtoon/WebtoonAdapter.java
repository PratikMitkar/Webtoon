package com.example.webtoon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class WebtoonAdapter extends RecyclerView.Adapter<WebtoonAdapter.WebtoonViewHolder> {

    private Context context;
    private List<Webtoon> webtoonList;
    private SharedPreferences sharedPreferences;

    public WebtoonAdapter(Context context, List<Webtoon> webtoonList) {
        this.context = context;
        this.webtoonList = webtoonList;
        this.sharedPreferences = context.getSharedPreferences("Favorites", Context.MODE_PRIVATE);
    }

    // Method to update the adapter's dataset
    public void updateData(List<Webtoon> newWebtoonList) {
        this.webtoonList = newWebtoonList;
        notifyDataSetChanged();  // Notify the adapter to refresh the RecyclerView
    }

    @NonNull
    @Override
    public WebtoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_webtoon, parent, false);
        return new WebtoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebtoonViewHolder holder, int position) {
        Webtoon webtoon = webtoonList.get(position);
        holder.tvTitle.setText(webtoon.getTitle());
        holder.tvDescription.setText(webtoon.getDescriptions().get(0)); // Short description

        Glide.with(context)
                .load(webtoon.getImages().get(0)) // Load first image
                .into(holder.imageView);

        // Check if this webtoon is in favorites
        if (sharedPreferences.contains(webtoon.getTitle())) {
            holder.favoriteHeartIcon.setImageResource(R.drawable.ic_heart_filled); // Filled heart
        } else {
            holder.favoriteHeartIcon.setImageResource(R.drawable.ic_heart_empty); // Empty heart
        }

        // Handle click to toggle heart icon and save to favorites
        holder.favoriteHeartIcon.setOnClickListener(view -> {
            if (sharedPreferences.contains(webtoon.getTitle())) {
                // Remove from favorites
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(webtoon.getTitle());
                editor.apply();
                holder.favoriteHeartIcon.setImageResource(R.drawable.ic_heart_empty); // Change to empty heart
            } else {
                // Add to favorites
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(webtoon.getTitle(), webtoon.getTitle());
                editor.apply();
                holder.favoriteHeartIcon.setImageResource(R.drawable.ic_heart_filled); // Change to filled heart
            }
        });

        // Handle item click to open DetailActivity
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", webtoon.getTitle());
            intent.putExtra("writer", webtoon.getWriter());
            intent.putExtra("artist", webtoon.getArtist());
            intent.putExtra("reads", webtoon.getReads());
            intent.putExtra("images", (Serializable) webtoon.getImages());
            intent.putExtra("subtitles", (Serializable) webtoon.getSubtitles());
            intent.putExtra("descriptions", (Serializable) webtoon.getDescriptions());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return webtoonList != null ? webtoonList.size() : 0;
    }

    public static class WebtoonViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;
        ImageView imageView, favoriteHeartIcon;

        public WebtoonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imageView = itemView.findViewById(R.id.imageView);
            favoriteHeartIcon = itemView.findViewById(R.id.favoriteHeartIcon);
        }
    }
}
