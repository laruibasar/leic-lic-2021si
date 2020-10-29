package pt.isel.ls.utils;

import java.util.ArrayList;

public class CommandResult <T>{

    //vai ter os resultados dos queries executados à base de dados
    private ArrayList<T> result = new ArrayList<>();

    public CommandResult() {

    }
}
