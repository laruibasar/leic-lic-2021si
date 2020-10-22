package pt.isel.ls.services;

import pt.isel.ls.utils.Command;

public class Handler {

    private Command cmd;

    public Handler(String cmd) {
        this.cmd = new Command(cmd);
    }

}
