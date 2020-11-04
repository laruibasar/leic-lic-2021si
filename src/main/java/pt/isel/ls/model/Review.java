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

    @Override
    public String toString() {
        switch (selector) {
            case 1:
                //impressão de um comentário
                return rating +
                        "\n\n" +
                        summary +
                        "\n" +
                        movieCritic +
                        "\n\n" +
                        completeReview +
                        "\n\n MovieID = " +
                        movie +
                        " \nReviewID = " +
                        rid;
            case 2:
                //impressão de todos os comentários
                return "Stars "+
                        rating +
                        "\n\n" +
                        summary +
                        "\n" +
                        movieCritic +
                        "\n\n MovieID = " +
                        movie +
                        " \nReviewID = " +
                        rid;

            default:
                return null;
        }
    }
}
