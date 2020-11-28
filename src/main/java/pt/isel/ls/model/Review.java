package pt.isel.ls.model;

public class Review extends Model {

    private int rid;
    private String summary;
    private String completeReview;
    private int rating;
    private int movie;
    private int movieCritic;

    public Review(int rid, int movie){
        this.rid = rid;
        this.movie = movie;
    }

    public Review(String summary, String completeReview, int movie, int rating, int critic) {
        this.summary = summary;
        this.completeReview = completeReview;
        this.rating = rating;
        this.movie = movie;
        movieCritic = critic;
    }

    public Review(int rid, String summary, int movie, int rating) {
        this.rid = rid;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
    }

    public Review(int rid, String summary, String completeReview, int rating, int movie, int critic) {
        this.rid = rid;
        this.completeReview = completeReview;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
        this.movieCritic = critic;
    }

    public void setId(int rid) {
        this.rid = rid;
    }

    public int getId() {
        return rid;
    }

    public String getSummary() {
        return summary;
    }

    public String getCompleteReview() {
        return completeReview;
    }

    public int getRating() {
        return rating;
    }

    public int getMovie() {
        return movie;
    }

    public int getMovieCritic() {
        return movieCritic;
    }

    public void setMovieCritic(int movieCritic) {
        this.movieCritic = movieCritic;
    }

    @Override
    public String toString() {
        if (completeReview == null) {
            return "ReviewID = " + rid
                    + "\nSummary = " + summary
                    + "\nStars =" + rating
                    + "\nMovieID = " + movie
                    + "\nMovieCritic = " + movieCritic;
        }
        return  "ReviewID = " + rid
                + "\nSummary = " + summary
                + "\nComplete Review = " + completeReview
                + "\nStars = " + rating
                + "\nMovieID = " + movie
                + "\nMovie Critic = " + movieCritic;
    }
}
