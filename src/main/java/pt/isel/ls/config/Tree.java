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

<<<<<<< HEAD

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
=======
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
>>>>>>> 70d472368bface5f825e642076d5205a4c192c7d
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
