package pt.isel.ls.model;

import java.util.LinkedList;

public class Movie extends Model {

    private int mid;
    private String title;
    private int year;
    private LinkedList<Review> reviews;
    private int rating;

    private String genre;
    private String directors;
    private String actors;

    public Movie() {

    }

    public Movie(int mid, String title, int year, int rating) {
        this.mid = mid;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public Movie(int mid, String title) {
        this.mid = mid;
        this.title = title;
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Movie(int mid, String title, int year) {
        this.mid = mid;
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getMid() {
        return mid;
    }

    public void setId(int id) {
        mid = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setReviews(LinkedList<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        if (year == 0) {
            return "MovieID = " + mid + "\tTitle = " + title;
        }
        return "MovieID = " + mid + "\tTitle = " + title + "\tYear = " + year;
    }

}


