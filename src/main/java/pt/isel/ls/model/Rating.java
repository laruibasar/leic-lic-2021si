package pt.isel.ls.model;

public class Rating {

    private int rating;

    public Rating(int rating){
        if (rating < 1 || rating > 5) return; //do we create OutOfValuesException
        this.rating = rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) return; //do we create OutOfValuesException
        this.rating = rating;
    }
    public int getRating() { return rating; }

}
