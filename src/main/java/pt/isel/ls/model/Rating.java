package pt.isel.ls.model;

public class Rating extends Model {

    private int ratingId;
    private int movieId;
    private int rating;

    public Rating(int ratingId, int movieId, int rating) {
        this.ratingId = ratingId;
        this.movieId = movieId;
        this.rating = rating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getRating() {
        return rating;

    }

    @Override
    public String toString() {
        return "Rating = " + rating + "\nRating id = "
                + ratingId + "\nMovie id = " + movieId;
    }
}
