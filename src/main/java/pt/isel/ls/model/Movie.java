package pt.isel.ls.model;

public class Movie {

    private int mid;
    private String title;
    private int year;

    public Movie(int mid) {
        this.mid = mid;
    }

    public Movie(int mid, String title, int year) {
        this.mid = mid;
        this.title = title;
        this.year = year;
    }

    public int getMid() { return mid; }

    public String getTitle() {
        return title;
    }

    public int getYear() { return year; }
}


