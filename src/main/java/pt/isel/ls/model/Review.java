package pt.isel.ls.model;

public class Review {

    private String summary;
    private String completeReview;
    private Rating rating;
    private Movie movie;
    private User movieCritic;

    public Review(String summary, String completeReview, Rating rating,
                  Movie movie, User movieCritic) {

        this.summary = summary;
        this.completeReview = completeReview;
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

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }

    public void setMovieCritic(User movieCritic) {
        this.movieCritic = movieCritic;
    }

    public User getMovieCritic() {
        return movieCritic;
    }
}
