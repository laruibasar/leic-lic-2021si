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
        return p.parsePath(cmd);
    }

    public String getMethod(String cmd) {
        return p.parseMethod(cmd);
    }

    public String[] getParams(String cmd){
        if(method.equals("GET")) {
            params = p.paramsFromGET(cmd);
        }else if (method.equals("POST")){
            params = p.paramsFromPOST(cmd);
        }else {
            Exit();
        }
        return params;
    }

    public void Exit(){
        System.exit(0);
    }
}
