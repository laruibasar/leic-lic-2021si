package pt.isel.ls.view.html.Body;

import java.util.ArrayList;

public class table extends body {

    private ArrayList<String[]> rows;

    public table () {}

    public table(ArrayList<String[]> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\t\t<table>");
        for (String[] r : rows) {
            sb.append("\n" + new row(r));
        }
        sb.append("\n\t\t</table>");
        return sb.toString();
    }
}
