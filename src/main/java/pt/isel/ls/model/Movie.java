package pt.isel.ls.model;

import java.util.Date;

public class Movie {

    private String title;
    private Date releaseDate;
    //May also have this attributes
    private String genre;
    private String associatedPictures;
    private String castAndDirectors;

    public Movie(String title, Date releaseDate, String genre,
                 String associatedPictures, String castAndDirectors){

        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.associatedPictures = associatedPictures;
        this.castAndDirectors = castAndDirectors;
    }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public Date getReleaseDate() { return releaseDate; }

    public void setGenre(String genre) { this.genre = genre; }
    public String getGenre() { return genre; }

    public void setAssociatedPictures(String associatedPictures) {
        this.associatedPictures = associatedPictures;
    }
    public String getAssociatedPictures() { return associatedPictures; }

    public void setCastAndDirectors(String castAndDirectors) {
        this.castAndDirectors = castAndDirectors;
    }
    public String getCastAndDirectors() { return castAndDirectors; }

}
