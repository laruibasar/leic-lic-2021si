package pt.isel.ls.model;

public class Rating {

    private int rating;

    public Rating(int rating) throws RatingException {
        if (rating < 1 || rating > 5)
            throw new RatingException("Rating: value not allow");
        this.rating = rating;
    }

    public void setRating(int rating) throws RatingException{
        if (rating < 1 || rating > 5)
            throw new RatingException("Rating: value not allow");
        this.rating = rating;
    }
    public int getRating() { return rating; }

    public class RatingException extends Exception {
        public RatingException(String message) {
            super(message);
        }
    }
}
