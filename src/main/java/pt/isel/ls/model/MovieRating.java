package pt.isel.ls.model;

public class MovieRating extends Model {

    private int movieId;
    private float average;
    private int votesOne;
    private int votesTwo;
    private int votesThree;
    private int votesFour;
    private int votesFive;

    public MovieRating(int mid, float avg, int one,
                       int two, int three, int four, int five) {
        this.movieId = mid;
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

    @Override
    public String toString() {
        return "Number of votes: "
                + "\n\t1 = " + votesOne
                + "\n\t2 = " + votesTwo
                + "\n\t3 = " + votesThree
                + "\n\t4 = " + votesFour
                + "\n\t5 = " + votesFive;
    }
}
