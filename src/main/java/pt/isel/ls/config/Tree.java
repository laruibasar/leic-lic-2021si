package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;

import java.util.ArrayList;

public class Tree {
    private Node root;
    private ArrayList<Command> cmds = new ArrayList<>();

    public Node getRoot() {
        return root;
    }

    public Handler lookForHandler(Node node, Command cmd) {
        if (node == null)
            return null;
        if (node.equals(cmd))
            return node.getHandler();
        else {
            Handler handler = null;
            for (Node child : node.getChildren())
                if ((handler = lookForHandler(child, cmd)) != null)
                    return handler;
        }
        return null;
    }

    public Command findCommand(Node node, Command cmd) {
        if (node == null)
            return null;
        if (node.equals(cmd))
            return node.getCommand();
        else {
            Command command = null;
            for (Node child : node.getChildren())
                if ((command = findCommand(child, cmd)) != null)
                    return command;
        }
        return null;
    }


    public void buildTree(ArrayList<Node> leaves) {
        this.root = leaves.get(0);
        cmds.add(root.getCommand());
        int children = 1;
        for(Node l: leaves) {
            for(int i = 0; i < 2 && children < leaves.size(); i++, children++) {
                l.addChild(leaves.get(children));
                cmds.add(leaves.get(children).getCommand());
            }
        }
    }

    public ArrayList<Command> getAllCommands() {
        return cmds;
    }
}
