package pt.isel.ls.model;

public class Movie extends Model {

    private int mid;
    private String title;
    private int year;

    public Movie(int mid, int selector) {
        super(selector);
        this.mid = mid;
    }

    public Movie(int mid, String title, int year, int selector) {
        super(selector);
        this.mid = mid;
        this.title = title;
        this.year = year;
    }

    public int getMid() {
        return mid;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        switch (selector) {
            case 3:
                return "MovieId = " + mid + " Title = " + title + "Year = " + year;
            case 1:
                return "MovieID = " + mid;
            default:
                return null;
        }
    }

}


