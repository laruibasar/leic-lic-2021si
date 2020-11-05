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
                //impressão de um comentário
                return "Stars = "
                        +
                        rating
                        +
                        "Summary = "
                        +
                        summary
                        +
                        "Movie Critic = "
                        +
                        movieCritic
                        +
                        "Complete Review = "
                        +
                        completeReview
                        +
                        "MovieID = "
                        +
                        movie
                        +
                        "ReviewID = "
                        +
                        rid;
            case 5:
                //impressão de todos os comentários
                return "Stars ="
                        +
                        rating
                        +
                        "Summary = "
                        +
                        summary
                        +
                        "MovieCritic = "
                        +
                        movieCritic
                        +
                        "MovieID = "
                        +
                        movie
                        +
                        "ReviewID = "
                        +
                        rid;

            default:
                return null;
        }
    }
}
