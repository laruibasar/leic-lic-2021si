package pt.isel.ls.model.HTML;

import pt.isel.ls.model.User;

public class HTMLUser extends User {

    private int reviewId;
    private String reviewSummary;
    private int reviewRating;
    private int movieId;
    private String movieTitle;
    private int movieYear;

    public HTMLUser(int userId, String userName, String userEmail, int rid, String summary, int rating, int mid, String title, int year) {
        super(userId, userName, userEmail);
        reviewId = rid;
        reviewSummary = summary;
        reviewRating = rating;
        movieId = mid;
        movieTitle = title;
        movieYear = year;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(String reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
