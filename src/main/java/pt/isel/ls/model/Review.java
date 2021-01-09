package pt.isel.ls.model;

public class Review extends Model {

    private int rid;
    private String summary;
    private String completeReview;
    private int rating;
    private Movie movie;
    private User movieCritic;

    public Review(int rid, Movie movie) {
        this.rid = rid;
        this.movie = movie;
    }

    public Review(String summary, String completeReview, Movie movie, int rating, User critic) {
        this.summary = summary;
        this.completeReview = completeReview;
        this.rating = rating;
        this.movie = movie;
        movieCritic = critic;
    }

    public Review(int rid, String summary, Movie movie, int rating) {
        this.rid = rid;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
    }

    public Review(int rid, String summary, String completeReview, int rating, Movie movie, User critic) {
        this.rid = rid;
        this.completeReview = completeReview;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
        this.movieCritic = critic;
    }

    public Review(int rid, String summary, int rating, Movie movie) {
        this.rid = rid;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
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

    public Movie getMovie() {
        return movie;
    }

    public User getMovieCritic() {
        return movieCritic;
    }

    public void setMovieCritic(User movieCritic) {
        this.movieCritic = movieCritic;
    }

}
