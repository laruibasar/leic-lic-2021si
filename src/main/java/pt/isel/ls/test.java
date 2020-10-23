package pt.isel.ls;

import pt.isel.ls.services.Handler;
import pt.isel.ls.services.RateMovieHandler;
import pt.isel.ls.utils.Parser;

public class test {

    public static void main(String[] args) {
        //Parser p = new Parser("POST /users name=Daniel+Azevedo&email=azevedo_daniel1994");
        Parser p = new Parser("POST /movies/240/ratings rating=8&");
    }
}
