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

    /***
     * Parser is responsible for getting all parameters from the command given
     * @param cmd - > command given by user
     */
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

                    if(-1 == input[2].indexOf('&')) {
                        movieID = Integer.parseInt(subDirectories[2]);
                        aux = input[2].split("=");
                        rating = Integer.parseInt(aux[1]);

                        System.out.println("MovieID = "+movieID +" rating = "+rating);

                    }else{
                        //POST /movies/{mid}/reviews uid=546&reviewSummary=bad&review=horriblemoviemustnotbeseen&rating=0
                        //aux[0] = uid=546
                        //aux[1] = reviewSummary=bad
                        //aux[2] = review=horriblemoviemustnotbeseen
                        //aux[3] = rating=0
                        aux = input[2].split("&");
                        String[] paramParser;
                        paramParser = aux[0].split("=");
                        userID = Integer.parseInt(paramParser[1]);
                        paramParser = aux[1].split("=");
                        reviewSummary = paramParser[1];
                        paramParser = aux[2].split("=");
                        review = paramParser[1];
                        paramParser = aux[3].split("=");
                        rating = Integer.parseInt(paramParser[1]);

                        System.out.println("userID: "+userID
                                + " reviewSummary: "+reviewSummary
                                + " review: "+review
                                + " rating: "+rating);
                    }
                }
                break;
            case "GET":

                //GET /users/{uid} - returns the details for the user identified by uid
                if(subDirectories[1].equals("users") && subDirectories.length == 3) {
                    userID = Integer.parseInt(subDirectories[2]);

                    System.out.println("userID = "+userID);

                //GET /movies/{mid}
                //GET /movies/{mid}/ratings
                //GET /movies/{mid}/reviews
                }else if(subDirectories[1].equals("movies") && (subDirectories.length == 2 || subDirectories.length == 3)) {
                    movieID = Integer.parseInt(subDirectories[2]);

                    System.out.println("movieID ="+movieID);

                //GET /movies/{mid}/reviews/{rid}
                }else if(subDirectories[1].equals("movies") && subDirectories.length == 5) {
                    movieID = Integer.parseInt(subDirectories[2]);
                    reviewID = Integer.parseInt(subDirectories[4]);

                    System.out.println("movieID ="+movieID +" reviewID" + reviewID);

                //GET /users/{uid}/reviews
                }else if(subDirectories[1].equals("users") && subDirectories.length == 4) {
                    userID = Integer.parseInt(subDirectories[2]);

                    System.out.println("userID = "+userID);

                //GET /users/{uid}/reviews/{rid}
                }else if(subDirectories[1].equals("users") && subDirectories.length == 5) {
                    userID = Integer.parseInt(subDirectories[2]);
                    reviewID = Integer.parseInt(subDirectories[4]);

                //GET /tops/rating
                    /**
                     * TODO: Average must have only two values.
                     *  Make maybe a break if that fails
                     */
                }else if(subDirectories[1].equals("tops") && subDirectories.length == 3) {
                    //GET /tops/ratings n=10&average=highest&min=5
                    //aux[0] = n=10
                    //aux[1] = average=highest
                    //aux[2] = min=5
                    aux = input[2].split("&");
                    String[] paramParser;
                    paramParser = aux[0].split("=");
                    nMovies = Integer.parseInt(paramParser[1]);
                    paramParser = aux[1].split("=");
                    average = paramParser[1];
                    paramParser = aux[2].split("=");
                    minVotes = Integer.parseInt(paramParser[1]);

                    System.out.println("nMovies: "+nMovies
                            + " average: "+average
                            + " minVotes: "+minVotes);
                }

                /**
                 * TODO: create some kind of error, since the method isnt valid
                 */
            default:
                break;

        }
    }


}
