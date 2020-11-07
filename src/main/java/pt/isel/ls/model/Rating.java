package pt.isel.ls.model;

public class Rating extends Model {

    private int ratingId;
    private int movieId;
    private int rating;

    public Rating(int ratingId, int rating, int movieId) {
        this.ratingId = ratingId;
        this.movieId = movieId;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating = " + rating + "\nRating id = "
                + ratingId + "\nMovie id = " + movieId;
    }
}
