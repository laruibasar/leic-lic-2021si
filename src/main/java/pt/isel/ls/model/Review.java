package pt.isel.ls.model;

public class Review {

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

    public Review(int rid, String completeReview, String summary, int movie, int rating, int movieCritic) {

        this.rid = rid;
        this.completeReview = completeReview;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
        this.movieCritic = movieCritic;
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
}
