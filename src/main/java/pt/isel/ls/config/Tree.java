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
                if (child.getCommand().getMethod() == cmd.getMethod()) {
                    if ((handler = lookForHandler(child, cmd)) != null) {
                        return handler;
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @param node root of tree (first time method is called)
     * @param cmd Command to search for
     * @return null if Command is not found, or the respective Command
     *
     * search method
     *
     *                                   1º
     *                          2º if child.getMethod == cmd.getMethod
     *                          3º
     *               4º                   11º
     *          5º        8º         12º       15º
     *      6º    7º  9º     10º 13º   14º 16º     17º
     */
    public Command findCommand(Node node, Command cmd) {
        if (node == null) {
            return null;
        }
        if (node.equals(cmd)) {
            return node.getCommand();
        } else {
            Command command = null;
            for (Node child : node.getChildren()) {
                if (child.getCommand().getMethod() == cmd.getMethod()) {
                    if ((command = findCommand(child, cmd)) != null) {
                        return command;
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @param leaves tree nodes
     * @param commandTypes number of different type of Command methods
     * Every command method type has is own tree, in order to speed the search.
     *
     * Tree formation
     *
     *                                                 listen /
     *
     *            get /                     post /                      delete /           option /        exit /
     *
     *            get /                     post /                      delete /
     *      get /      get /            post /     post /          delete /     delete /
     * get /  get / get /  get /   post /  post / post /  post /
     *                     ......                       .....                       ........
     */
    public void buildTree(ArrayList<Node> leaves, int commandTypes) {

        //get all commands ir order to display Command -> OPTION /
        setCommands(leaves);

        //Root is the listen method
        this.root = leaves.get(0);

        //position of the second children level, left side
        int index = commandTypes;

        //add add remaining commands as children of the root (Method LISTEN /)
        for (int i = 1; i < commandTypes; i++) {
            this.root.addChild(leaves.get(i));
            if (index < leaves.size() - 1) {
                index = subTreeGenerator(leaves.get(i), index, i, leaves);
            }
        }
    }


    /**
     * Add all children to the respective Method type, making an sub-tree
     * @param node parent node
     * @param start position of first children
     * @param parent root of this subtree
     * @param leaves tree nodes list
     * @return position of the next children type, for other parent type
     */
    public int subTreeGenerator(Node node, int start, int parent, ArrayList<Node> leaves) {

        int index = start + 1;
        leaves.get(parent).addChild(leaves.get(start));

        while (index < leaves.size() &&
                leaves.get(index).getCommand().getMethod() == node.getCommand().getMethod()) {

            for (int i = 0; index < leaves.size() && i < 2; ++i) {
                if (leaves.get(index).getCommand().getMethod() == node.getCommand().getMethod()) {
                    leaves.get(start).addChild(leaves.get(index++));
                }
            }
            ++start;
        }
        return index;
    }

    private void setCommands(ArrayList<Node> leaves) {
        for (Node l: leaves) {
            commands.add(l.getCommand());
        }
    }

    public ArrayList<Command> getAllCommands() {
        return commands;
    }
}
