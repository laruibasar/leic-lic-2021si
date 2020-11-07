package pt.isel.ls.model;

public class Review extends Model {

    private int rid;
    private String summary;
    private String completeReview;
    private int rating;
    private int movie;
    private int movieCritic;

    public Review(int rid, String summary, int movie, int rating) {
        this.rid = rid;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
    }

    public Review(int rid, String completeReview, String summary, int movie, int rating, int critic) {
        this.rid = rid;
        this.completeReview = completeReview;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
        this.movieCritic = critic;
    }

    public String getSummary() {
        return summary;
    }

    public int getRating() {
        return rating;
    }

    public int getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        if (completeReview == null) {
            return "Stars =" + rating
                    + "\nMovieCritic = " + movieCritic
                    + "\nSummary = " + summary;
        }
        return "Stars = " + rating
                + "\nSummary = " + summary
                + "\nMovie Critic = " + movieCritic
                + "\n\nComplete Review = " + completeReview
                + "\nMovieID = " + movie
                + "\nReviewID = " + rid;
    }
}
