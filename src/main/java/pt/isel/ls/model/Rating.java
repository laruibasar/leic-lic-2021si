package pt.isel.ls.model;

public class Rating {

    private float average;
    private int votesOne;
    private int votesTwo;
    private int votesThree;
    private int votesFour;
    private int votesFive;

    public Rating(float average, int votesOne, int votesTwo, int votesThree, int votesFour, int votesFive) throws RatingException {
        if (average < 1 || average > 5) {
            throw new RatingException("Average: value not allow");
        }
        this.average = average;
        this.votesOne = votesOne;
        this.votesTwo = votesTwo;
        this.votesThree = votesThree;
        this.votesFour = votesFour;
        this.votesFive = votesFive;
    }

    public float getAverage() { return average; }
    public int getVotesOne() { return votesOne; }
    public int getVotesTwo() { return votesTwo; }
    public int getVotesThree() { return votesThree; }
    public int getVotesFour() { return votesFour; }
    public int getVotesFive() { return votesFive; }

    public class RatingException extends Exception {
        public RatingException(String message) {
            super(message);
        }
    }
}
