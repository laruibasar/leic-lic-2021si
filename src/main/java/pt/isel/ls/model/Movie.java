package pt.isel.ls.model;

public class Movie {

    private int mid;
    private String title;
    private int year;
    private String genre;
    private String castAndDirectors;

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

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre() {
        return genre;
    }
    public void setCastAndDirectors(String castAndDirectors) {
        this.castAndDirectors = castAndDirectors;
    }
    public String getCastAndDirectors() {
        return castAndDirectors;
    }
}
