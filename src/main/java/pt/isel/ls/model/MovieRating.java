package pt.isel.ls.model;

public class MovieRating extends Model {

    private int movieId;
    private float average;
    private int votesOne;
    private int votesTwo;
    private int votesThree;
    private int votesFour;
    private int votesFive;

    public MovieRating(int mid, float avg, int one, int two, int three, int four, int five) throws RatingException {
        if (avg < 1 || avg > 5) {
            throw new RatingException("Average: value not allow");
        }
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

    public class RatingException extends Exception {
        public RatingException(String message) {
            super(message);
        }
    }

    @Override
    public String toString() {
        return null;
    }
}
