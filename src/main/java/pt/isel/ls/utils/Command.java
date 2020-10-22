package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private String method;
    private String [] path;
    private String [] parameters;
    private boolean verified = false;
    private static List<String> templates = new ArrayList<>();

    //Templates pré definidos

    /****
     * TODO implementar forma de permitir verificação da diretoria /movies/{mid}/ratings
     */
    static {
        templates.add("/users");
        templates.add("/movie");
        templates.add("/tops/ratings");
    }

    public Command(String cmd) {
        String[] input = cmd.split(" ");
        methodVerifier(method = input[0]);
        pathVerifier(path = input[1].split("/"));
    }

    private int pathVerifier(String[] strings) {
        return 0;
    }

    public int methodVerifier(String method) {
        return 0;
    }

    //Estrutura de dados com complexidade O(1) para uma procura
    public boolean verifyTemplate(String template) {
        return templates.contains(template);
    }
}
