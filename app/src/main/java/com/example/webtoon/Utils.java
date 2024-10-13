package com.example.webtoon;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class Utils {

    // Method to load and parse the JSON file from the assets folder
    public static List<Webtoon> loadWebtoons(Context context) {
        String json = null;
        try {
            // Open the JSON file in the assets folder
            InputStream is = context.getAssets().open("webtoons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        // Use Gson to parse the JSON into a List of Webtoon objects
        Gson gson = new Gson();
        Type webtoonListType = new TypeToken<List<Webtoon>>() {}.getType();
        return gson.fromJson(json, webtoonListType);
    }

    // Method to fetch a webtoon by title
    public static Webtoon getWebtoonByTitle(Context context, String title) {
        List<Webtoon> webtoons = loadWebtoons(context);
        for (Webtoon webtoon : webtoons) {
            if (webtoon.getTitle().equals(title)) {
                return webtoon;
            }
        }
        return null;  // Return null if not found
    }
}
