package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Command command;
    private Handler handler;
    private List<Node> children = new ArrayList<>();
    private Node parent;

    public Node(Command c, Handler h) {
        command = c;
        handler = h;
    }

    public Handler getHandler() {
        return handler;
    }

    public Command getCommand() {
        return command;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        child.setParent(this);
        children.add(child);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean equals(Command cmd) {
        if (null == cmd) {
            return false;
        }
        if (cmd.toString().equals(this.command.toString())) {
            return true;
        }
        return false;
    }
}
