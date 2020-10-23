package pt.isel.ls.utils;

public class Command {

    private String method;
    private String path;
    private String [] params;

    /***
     * A Parser object is responsible for handling the information contained
     * in a command coming from the user input, the methods used
     * to fill the fields of this class, are used to obtain
     * the information extracted by the command's Parser.
     */
    public Command(String cmd) {
        Parser p = new Parser(cmd);
        this.method = p.getMethod();
        this.path = p.getPath();
        this.params = p.getParams();

        //for test
        System.out.print("Method = "+method +" ");
        for(String par: params) {
            System.out.print("Parameter: " + par +" ");
        }
        System.out.print(" Path = "+ path +"\n\n");
    }
}
