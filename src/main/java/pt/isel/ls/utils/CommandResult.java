package pt.isel.ls.utils;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CommandResult {

    //vai ter os resultados dos queries executados à base de dados
    private ArrayList<ResultSet> result = new ArrayList<>();

    public CommandResult(ResultSet rs) {
        result.add(rs);
    }
}
