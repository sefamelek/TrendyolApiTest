package com.trendyol;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)


public class JsonParameter {


    @JsonProperty("imdbID")
    private String Imdb;

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;


    public String getImdb() {
        return Imdb;
    }

    public void setImdb(String imdb) {
        this.Imdb = imdb;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}