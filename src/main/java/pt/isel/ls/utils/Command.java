package pt.isel.ls.utils;

public class Command {

    private String method;
    private String path;
    private String [] params;
    private Parser p;

    /***
     * A Parser object is responsible for handling the information contained
     * in a command coming from the user input, the methods used
     * to fill the fields of this class, are used to obtain
     * the information extracted by the command's Parser.
     */
    public Command(String cmd) {
        p = new Parser();
        this.path = getPath(cmd);
        this.method = getMethod(cmd);
        this.params = getParams(cmd);

        //for test
        System.out.print("Method = "+method +" ");
        for(String par: params) {
            System.out.print("Parameter: " + par +" ");
        }
        System.out.print(" Path = "+ path +"\n\n");
    }

    public String getPath(String cmd) {
        String[] pathExtractor = cmd.split(" ");
        return path = pathExtractor[1];
    }

    public String getMethod(String cmd) {
        String[] methodExtractor = cmd.split(" ");
        return method = methodExtractor[0];
    }

    public String[] getParams(String cmd){
        String[] input = cmd.split(" ");
        if(method.equals("GET")) {
            params = p.paramsFromGET(input);
        }else if (method.equals("POST")){
            params = p.paramsFromPOST(input);
        }else {
            Exit();
        }
        return params;
    }

    public void Exit(){
        System.exit(0);
    }
}
