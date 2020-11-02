package pt.isel.ls.model;

public class Review {

    private int rid;
    private String summary;
    private String completeReview;
    private int rating;
    private int movie;
    private int movieCritic;

    public Review(int rid, String summary, int rating,
                  int movie, int movieCritic) {

        this.rid = rid;
        this.summary = summary;
        this.rating = rating;
        this.movie = movie;
        this.movieCritic = movieCritic;

    }

    public void setCompleteReview(String completeReview) {
        this.completeReview = completeReview;
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
