package pt.isel.ls.view.html.body;

import java.util.ArrayList;

public class Table extends Body {

    private ArrayList<String[]> rows;

    public Table() {
    }

    public Table(ArrayList<String[]> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\t\t<table>");
        for (String[] r : rows) {
            sb.append("\n" + new Row(r));
        }
        sb.append("\n\t\t</table>");
        return sb.toString();
    }
}
