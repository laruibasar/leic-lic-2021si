package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private String method;
    private String [] path;
    private String [] parameters;


    /***
     * TODO Criar exceção para caso o retorno seja 1 ?
     * methodVerifier
     * pathVerifier
     */
    public Command(String cmd) {
        Parser p = new Parser(cmd);
    }




    /***
     * Verifies method from the command given
     * @param method
     * @return
     */
    public int methodVerifier(String method) {
        return (method.equals("GET") || method.equals("POST")) ? 0:1;
    }
}
