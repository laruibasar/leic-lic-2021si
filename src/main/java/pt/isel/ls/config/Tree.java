package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;

import java.util.ArrayList;

public class Tree {
    private Node root;
    private ArrayList<Command> commands = new ArrayList<>();

    public Node getRoot() {
        return root;
    }

    public Handler lookForHandler(Node node, Command cmd) {
        if (node == null) {
            return null;
        }
        if (node.equals(cmd)) {
            return node.getHandler();
        } else {
            Handler handler = null;
            for (Node child : node.getChildren()) {
                if ((handler = lookForHandler(child, cmd)) != null &&
                child.getCommand().getMethod() == cmd.getMethod()) {
                    return handler;
                }
            }
        }
        return null;
    }

    public Command findCommand(Node node, Command cmd) {
        if (node == null) {
            return null;
        }
        if (node.equals(cmd)) {
            return node.getCommand();
        } else {
            Command command = null;
            for (Node child : node.getChildren()) {
                if ((command = findCommand(child, cmd)) != null &&
                        child.getCommand().getMethod() == cmd.getMethod()) {
                    return command;
                }
            }
        }
        return null;
    }


    public void buildTree(ArrayList<Node> leaves, int commandTypes) {

        //get all commands to display OPTION / command
        setCommands(leaves);

        //Root is the listen method
        this.root = leaves.get(0);

        int index = commandTypes;
        //add all app methods as children to root
        for (int i = 1; i < commandTypes + 1; i++) {
            this.root.addChild(leaves.get(i));
            if (index < leaves.size() - 1) {
                index = insert(leaves.get(i), index, i, leaves);
            }
        }
    }

    private void setCommands(ArrayList<Node> leaves) {
        for (Node l: leaves) {
            commands.add(l.getCommand());
        }
    }

    //acrescenta todos os  childs ao respetivo method, e retorna o índice onde foi interrompido
    public int insert(Node node, int start, int parent, ArrayList<Node> leaves) {
        int index = start + 1;
        leaves.get(parent).addChild(leaves.get(start));
        while(leaves.get(index).getCommand().getMethod() == node.getCommand().getMethod()
                && index < leaves.size() - 1) {

            for (int i = 0; index < leaves.size() - 1 && i < 2; i++, index++) {
                leaves.get(start).addChild(leaves.get(index));
            }
            ++start;
        }

        //position for next
        return index;
    }

    public ArrayList<Command> getAllCommands() {
        return commands;
    }
}
