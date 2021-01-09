package pt.isel.ls.model;

public class Rating extends Model {

    private int ratingId;
    private Movie movie;
    private int rating;

    public Rating(int ratingId, Movie movie, int rating) {
        this.ratingId = ratingId;
        this.movie = movie;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public Movie getMovieId() {
        return movie;
    }

    public int getRating() {
        return rating;
    }
}
