package pt.isel.ls.model;

public class Movie extends Model {

    private int mid;
    private String title;
    private int year;

    public Movie(int mid, String title) {
        this.mid = mid;
        this.title = title;
    }

    public Movie(int mid, String title, int year) {
        this.mid = mid;
        this.title = title;
        this.year = year;
    }

    @Override
    public String toString() {
        if (year == 0) {
            return "MovieID = " + mid + " \nTitle = " + title;
        }
        return "MovieID = " + mid + " \nTitle = " + title + " \nYear = " + year;
    }

}


