package pt.isel.ls.utils;

public class Parser {


    /**
     *TODO: No máximo tenho 4 parâmetros, avaliar número de campos.
     */
    private int userID;
    private int reviewID;
    private int movieID;
    private String movieTitle;
    private int releaseYear;
    private int rating;
    private String reviewSummary;
    private String review;
    private int nMovies;
    private String average;
    private int minVotes;
    private String firstName;
    private String lastName;
    private String mail;


    public Parser(String cmd) {
        String[] input = cmd.split(" ");
        String[] subDirectories = input[1].split("/");
        String[] aux;
        switch (input[0]) {
            case "POST":

                //POST /users - creates a new user
                if(subDirectories[1].equals("users") && subDirectories.length == 2) {
                    //aux[0] = name
                    //aux[1] = First+Last&email
                    //aux[2] = example@email.com
                    aux = input[2].split("=");
                    mail = aux[2];

                    //result[0] = first;
                    //resutl[1] = last&email
                    String result[] = aux[1].split("\\+");
                    firstName = result[0];

                    //aux[0] = last
                    aux = result[1].split("&");
                    lastName = aux[0];

                    System.out.println("Mail -> " + this.mail + " FirstName :" + firstName + " SecondName :" + lastName);

                //POST /movies - creates a new movie
                }else if (subDirectories[1].equals("movies") && subDirectories.length == 2) {
                    //initial parameters String -> /movies title=Gatsby&releaseYear=2013
                    //aux[0] = title
                    //aux[1] = Gatsby
                    //aux[2] = releaseYear
                    //aux[3] = 2013
                    aux = input[2].split("=");
                    movieTitle = aux[1];
                    releaseYear = Integer.parseInt(aux[3]);

                    System.out.println("Movie title = "+ movieTitle + " releaseYear = "+ releaseYear);

                //POST /movies/{mid}/reviews - creates a new review for the movie identified by mid
                }else if(subDirectories[1].equals("movies") && subDirectories.length == 4) {

                    if(-1 != input[2].indexOf('&')) {
                        movieID = Integer.parseInt(subDirectories[2]);
                        aux = input[2].split("=");
                        rating = Integer.parseInt(aux[1]);

                        System.out.println("MovieID = "+movieID +" rating = "+rating);

                    }else{
                        aux = input[2].split("=");
                    }
                }else if() {

                }
                break;
            default:
                break;

        }
    }


}
