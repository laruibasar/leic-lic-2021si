package pt.isel.ls.model;

public class MovieRating extends Model {

    private Movie movie;
    private float average;
    private int votesOne;
    private int votesTwo;
    private int votesThree;
    private int votesFour;
    private int votesFive;

    public MovieRating(Movie movie, float avg, int one,
                       int two, int three, int four, int five) {
        this.movie = movie;
        this.average = avg;
        this.votesOne = one;
        this.votesTwo = two;
        this.votesThree = three;
        this.votesFour = four;
        this.votesFive = five;
    }

    public float getAverage() {
        return average;
    }

    public int getMovieId() {
        return movie.getMid();
    }

    public int getVotesOne() {
        return votesOne;
    }

    public int getVotesTwo() {
        return votesTwo;
    }

    public int getVotesThree() {
        return votesThree;
    }

    public int getVotesFour() {
        return votesFour;
    }

    public int getVotesFive() {
        return votesFive;
    }

    @Override
    public String toString() {
        return    "Movie id =" + movie.getMid()
                + "\tAverage Rating = " + average
                + "\nNumber of votes: "
                + "\n\t1 = " + votesOne
                + "\n\t2 = " + votesTwo
                + "\n\t3 = " + votesThree
                + "\n\t4 = " + votesFour
                + "\n\t5 = " + votesFive;
    }
}
