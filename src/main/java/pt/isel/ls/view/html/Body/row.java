package pt.isel.ls.view.html.Body;

public class row extends table {

    private String[] columns;

    public row (String[] columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\t\t\t<tr>");
        for(String c: columns) {
            sb.append("\n\t\t\t\t<td>"
                    + c
                    + "</td>");
        }
        sb.append("\n\t\t\t</tr>");
        return sb.toString();
    }
}
