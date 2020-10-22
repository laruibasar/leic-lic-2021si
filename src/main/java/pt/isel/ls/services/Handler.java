package pt.isel.ls.services;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Parser;


/***
 * Esta classe executa um comando
 */
public abstract class Handler {

    private String template;
    private Command cmd;

    public Handler() {}

    public Handler(String template) {
        this.template = template;
        this.cmd = setCommmand(template);
    }

    public Command setCommmand(String template){
        Parser p = new Parser(template);
        return null;
    }
}
