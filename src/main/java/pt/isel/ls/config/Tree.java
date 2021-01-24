package pt.isel.ls.config;

import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.utils.Command;

import java.util.ArrayList;

public class Tree {
    private Node root;

    /**Serve para listar os comandos disponíveis para o utilizador "OPTION /"*/
    private ArrayList<Command> commands = new ArrayList<>();

    public Node getRoot() {
        return root;
    }

    /**
                                1º
                    2º                        9º
              3º         6º             10º        13º
           4º   5º    7º    8º      11º    12º  14º   15º

     */
    public Handler lookForHandler(Node node, Command cmd) {
        if (node == null) {
            return null;
        }
        if (node.equals(cmd)) {
            return node.getHandler();
        } else {
            Handler handler = null;
            for (Node child : node.getChildren()) {
                if ((handler = lookForHandler(child, cmd)) != null) {
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
                if ((command = findCommand(child, cmd)) != null) {
                    return command;
                }
            }
        }
        return null;
    }

    /**
     * Cada folha da árvore têm no máximo dois filhos próximos.
     * A raíz da árvore é o primeiro elemento do arrayList.
     *
     * @param leaves arrayList of Router commands
     */
    public void buildTree(ArrayList<Node> leaves) {
        this.root = leaves.get(0);
        commands.add(root.getCommand());
        int leaf = 1;
        for (Node l: leaves) {
            for (int i = 0; i < 2 && leaf < leaves.size(); i++, leaf++) {
                l.addChild(leaves.get(leaf));
                commands.add(leaves.get(leaf).getCommand());
            }
        }
    }

    public ArrayList<Command> getAllCommands() {
        return commands;
    }
}
