package com.example.webtoon;

import java.io.Serializable;
import java.util.List;

public class Webtoon implements Serializable {
    private String title;
    private String writer;
    private String artist;
    private String reads;
    private List<String> images;
    private List<String> subtitles;
    private List<String> descriptions;

    public Webtoon(String title, String writer, String artist, String reads, List<String> images, List<String> subtitles, List<String> descriptions) {
        this.title = title;
        this.writer = writer;
        this.artist = artist;
        this.reads = reads;
        this.images = images;
        this.subtitles = subtitles;
        this.descriptions = descriptions;
    }

    // Getters
    public String getTitle() { return title; }
    public String getWriter() { return writer; }
    public String getArtist() { return artist; }
    public String getReads() { return reads; }
    public List<String> getImages() { return images; }
    public List<String> getSubtitles() { return subtitles; }
    public List<String> getDescriptions() { return descriptions; }
}
