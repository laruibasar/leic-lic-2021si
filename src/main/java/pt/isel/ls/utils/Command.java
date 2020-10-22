package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private String method;
    private String [] path;
    private String [] parameters;
    private static List<String> templates = new ArrayList<>();

    //Templates pré definidos
    /****
     * TODO implementar forma de permitir verificação da diretoria /movies/{mid}/ratings
     */
    static {
        templates.add("users");
        templates.add("movie");
        templates.add("tops");
        templates.add("movies");
        templates.add("ratings");
        templates.add("reviews");
    }

    /***
     * TODO Criar exceção para caso o retorno seja 1 ?
     * methodVerifier
     * pathVerifier
     */
    public Command(String cmd) {
        String[] input = cmd.split(" ");
        methodVerifier(method = input[0]);
        pathVerifier(path = input[1].split("/"));
        parameters = parametersVerifier(input[2]);
    }

    /***
     * TODO improve code
     * @param params
     * @return
     */
    private String[] parametersVerifier(String params) {
        String aux[] = params.split("=");

        //result[0] = first; resutl[1] = last&email
        String result[] = aux[1].split("\\+");

        //result[3] = mail;
        result[3] = aux[3];
        aux = result[2].split("&");

        //result[2] = last;
        result[2] = aux[1];
        return result;
    }

    private int pathVerifier(String[] paths) {
        for(String s: paths) {
            if(!verifyTemplate(s));
                return 1;
        }
        return 0;
    }

    /***
     * Verifies method from the command given
     * @param method
     * @return
     */
    public int methodVerifier(String method) {
        return (method.equals("GET") || method.equals("POST")) ? 0:1;
    }

    /***
     * Verifies the directory given by the user,
     * with complexity O(1)
     * @param template
     * @return
     */
    public boolean verifyTemplate(String template) {
        return templates.contains(template);
    }
}
