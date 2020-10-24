package pt.isel.ls.model;

public class Movie {

    private String title;
    private int year;
    //May also have this attributes
    private String genre;
    private String associatedPictures;
    private String castAndDirectors;

    public Movie(String title, int year, String genre,
                 String associatedPictures, String castAndDirectors) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.associatedPictures = associatedPictures;
        this.castAndDirectors = castAndDirectors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setReleaseDate(int year) {
        this.year = year;
    }

    public int getReleaseDate() {
        return year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setAssociatedPictures(String associatedPictures) {
        this.associatedPictures = associatedPictures;
    }

    public String getAssociatedPictures() {
        return associatedPictures;
    }

    public void setCastAndDirectors(String castAndDirectors) {
        this.castAndDirectors = castAndDirectors;
    }

    public String getCastAndDirectors() {
        return castAndDirectors;
    }
}
