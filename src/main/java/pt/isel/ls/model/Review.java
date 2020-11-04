package pt.isel.ls.model;

public class Review extends Model {

    private int rid;
    private String summary;
    private String completeReview;
    private int rating;
    private int movie;
    private int movieCritic;

    public Review(int rid, String summary, int movie, int rating, int selector) {
        super(selector);
        this.rid = rid;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
    }

    public Review(int rid, String completeReview, String summary, int movie, int rating, int critic, int selector) {
        super(selector);
        this.rid = rid;
        this.completeReview = completeReview;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
        this.movieCritic = critic;
    }

    public String getCompleteReview() {
        return completeReview;
    }

    public String getSummary() {
        return summary;
    }

    public int getMovie() {
        return movie;
    }

    public int getRating() {
        return rating;
    }

    public int getMovieCritic() {
        return movieCritic;
    }

    @Override
    public String toString() {
        switch (selector) {
            case 6:
                //GET SPECIFIC REVIEW
                return "Stars = " + rating
                        + "\nSummary = " + summary
                        + "\nMovie Critic = " + movieCritic
                        + "\n\nComplete Review = " + completeReview
                        + "\nMovieID = " + movie
                        + "\nReviewID = " + rid;
            case 5:
                //GET ALL REVIEWS
                return "Stars =" + rating
                        + "\nMovieCritic = " + movieCritic
                        + "\nSummary = " + summary ;
            case 7:
                //CREATE REVIEW
                return "uid =" + movieCritic
                        + "\nreviewSummary = " + summary
                        + "\nreview = " + completeReview
                        + "\nrating = " + rating
                        + "\nreviewID = " + rid;
            default:
                return null;
        }
    }
}
