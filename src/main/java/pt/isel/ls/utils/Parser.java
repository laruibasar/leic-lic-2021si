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


    //POST /users

    public Parser(String cmd) {
        String[] input = cmd.split(" ");
        String[] subDirectories = input[1].split("/");
        switch (input[0]) {
            case "POST":
                //POST /users - creates a new user
                if(subDirectories[1].equals("users") && subDirectories.length == 2) {
                    //aux[0] = name
                    //aux[1] = First+Last&email
                    //aux[2] = example@email.com
                    String aux[] = input[2].split("=");
                    mail = aux[2];

                    //result[0] = first;
                    //resutl[1] = last&email
                    String result[] = aux[1].split("\\+");
                    firstName = result[0];

                    //aux[0] = last
                    aux = result[1].split("&");
                    lastName = aux[0];

                    System.out.println("Mail -> " + this.mail + " FirstName :" + firstName + " SecondName :" + lastName);
                }else if (subDirectories[1].equals("movies")) {

                }
                break;
            default:
                break;

        }
        /**
         * POST
         * /users
         * name=First+Last&email=example@email.com
         */
//
//        methodVerifier(method = input[0]);
//        pathVerifier(path = input[1].split("/"));
//        parameters = parametersParser(input[2]);
    }


}
