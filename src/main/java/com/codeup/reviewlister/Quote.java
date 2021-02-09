package com.codeup.reviewlister;

public class Quote {

    private String quote;
    private String author;
    private long id;
    private int stars;

    public Quote(long id, String quote, String author, int stars) {
        this.id = id;
        this.quote = quote;
        this.author = author;
        this.stars = stars;
    }

    public Quote(String quote, String author, int stars) {
        this.quote = quote;
        this.author = author;
        this.stars = stars;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
