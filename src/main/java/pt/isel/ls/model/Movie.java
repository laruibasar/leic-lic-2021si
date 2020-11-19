package pt.isel.ls.model;

public class Movie extends Model {

    private int mid;
    private String title;
    private int year;

    public Movie(int mid, String title) {
        this.mid = mid;
        this.title = title;
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Movie(int mid, String title, int year) {
        this.mid = mid;
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public void setId(int id) {
        mid = id;
    }

    @Override
    public String toString() {
        if (year == 0) {
            return "MovieID = " + mid + "   Title = " + title;
        }
        return "MovieID = " + mid + "   Title = " + title + " Year = " + year;
    }

}


