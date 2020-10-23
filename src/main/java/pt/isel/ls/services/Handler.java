package pt.isel.ls.services;

import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.Parser;


/***
 * Object takes command messages from a Command and exports them.
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
        Command cmd = new Command(template);
        return cmd;
    }
}
