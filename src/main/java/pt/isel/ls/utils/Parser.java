package pt.isel.ls.utils;

public class Parser {


    /**
     *TODO: No máximo tenho 4 parâmetros, avaliar número de campos.
     */
    private String method;
    private String [] params = new String[4];

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
                method = "POST";

                //POST /users - creates a new user
                if(subDirectories[1].equals("users") && subDirectories.length == 2) {
                    //aux[0] = name
                    //aux[1] = First+Last&email
                    //aux[2] = example@email.com
                    aux = input[2].split("=");
                    params[2] = aux[2];

                    //result[0] = first;
                    //resutl[1] = last&email
                    String result[] = aux[1].split("\\+");
                    params[0] = result[0];

                    //aux[0] = last
                    aux = result[1].split("&");
                    params[1] = aux[0];

                    System.out.println("Mail -> " + params[2] + " FirstName :" + params[0] + " SecondName :" + params[1]);

                //POST /movies - creates a new movie
                }else if (subDirectories[1].equals("movies") && subDirectories.length == 2) {
                    //initial parameters String -> /movies title=Gatsby&releaseYear=2013
                    //aux[0] = title
                    //aux[1] = Gatsby
                    //aux[2] = releaseYear
                    //aux[3] = 2013
                    aux = input[2].split("=");
                    params[0] = aux[1];
                    params[1] = aux[3];

                    System.out.println("Movie title = "+ params[0] + " releaseYear = "+ params[1]);

                //POST /movies/{mid}/reviews - creates a new review for the movie identified by mid
                }else if(subDirectories[1].equals("movies") && subDirectories.length == 4) {

                    if(-1 == input[2].indexOf('&')) {
                        params[0] = subDirectories[2];
                        aux = input[2].split("=");
                        params[1] = aux[1];

                        System.out.println("MovieID = "+params[0] +" rating = "+params[1]);

                    }else{
                        //POST /movies/{mid}/reviews uid=546&reviewSummary=bad&review=horriblemoviemustnotbeseen&rating=0
                        //aux[0] = uid=546
                        //aux[1] = reviewSummary=bad
                        //aux[2] = review=horriblemoviemustnotbeseen
                        //aux[3] = rating=0
                        aux = input[2].split("&");
                        String[] paramParser;
                        paramParser = aux[0].split("=");
                        params[0] = paramParser[1];
                        paramParser = aux[1].split("=");
                        params[1] = paramParser[1];
                        paramParser = aux[2].split("=");
                        params[2] = paramParser[1];
                        paramParser = aux[3].split("=");
                        params[3] = paramParser[1];

                        System.out.println("userID: "+params[0]
                                + " reviewSummary: "+params[1]
                                + " review: "+params[2]
                                + " rating: "+params[3]);
                    }
                }
                break;
            case "GET":
                method = "GET";

                //GET /users/{uid} - returns the details for the user identified by uid
                if(subDirectories[1].equals("users") && subDirectories.length == 3) {
                    params[0] = subDirectories[2];

                    System.out.println("userID = "+params[0]);

                //GET /movies/{mid}
                //GET /movies/{mid}/ratings
                //GET /movies/{mid}/reviews
                }else if(subDirectories[1].equals("movies") && (subDirectories.length == 2 || subDirectories.length == 3)) {
                    params[0] = subDirectories[2];

                    System.out.println("movieID ="+params[0]);

                //GET /movies/{mid}/reviews/{rid}
                }else if(subDirectories[1].equals("movies") && subDirectories.length == 5) {
                    params[0] = subDirectories[2];
                    params[1] = subDirectories[4];

                    System.out.println("movieID ="+params[0] +" reviewID" + params[1]);

                //GET /users/{uid}/reviews
                }else if(subDirectories[1].equals("users") && subDirectories.length == 4) {
                    params[0] = subDirectories[2];

                    System.out.println("userID = "+params[0]);

                //GET /users/{uid}/reviews/{rid}
                }else if(subDirectories[1].equals("users") && subDirectories.length == 5) {
                    params[0] = subDirectories[2];
                    params[1] = subDirectories[4];

                    System.out.println("UserID ="+ params[0] +" ReviewID" + params[1]);

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
                    params[0] = paramParser[1];
                    paramParser = aux[1].split("=");
                    params[1] = paramParser[1];
                    paramParser = aux[2].split("=");
                    params[2] = paramParser[1];

                    System.out.println("nMovies: "+params[0]
                            + " average: "+params[1]
                            + " minVotes: "+params[2]);
                }

                /**
                 * TODO: create some kind of error, since the method isnt valid
                 */
            default:
                break;

        }
    }


    public String getMethod() {
        return method;
    }

    public String[] getPath() {
        return null;
    }

    public String[] getParams() {
        return params;
    }
}
