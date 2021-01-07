package pt.isel.ls.view.htmlOLD.body;

import java.util.ArrayList;

public class Table extends Body {
    private ArrayList<String> tableHeader;
    private ArrayList<String[]> rows;

    public Table() {
    }

    public Table(ArrayList<String[]> rows) {
        this.rows = rows;
    }

    public Table(ArrayList<String> header, ArrayList<String[]> rows) {
        tableHeader = header;
        this.rows = rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\t\t<table>\n");
        if (tableHeader != null) {
            sb.append("<thead>\n");
            sb.append("<tr>\n");
            for (String head : tableHeader) {
                sb.append("<th>").append(head).append("</th>");
            }
            sb.append("</tr>\n");
            sb.append("</thead>\n");
        }
        sb.append("<tbody>");
        for (String[] r : rows) {
            sb.append("\n" + new Row(r));
        }
        sb.append("\n</tbody>");
        sb.append("\n\t\t</table>");
        return sb.toString();
    }
}
