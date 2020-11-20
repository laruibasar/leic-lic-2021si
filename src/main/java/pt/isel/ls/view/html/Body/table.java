package pt.isel.ls.view.html.Body;

public class table extends body {

    private row r;

    public table (row r) {
        this.r = r;
    }

    public table() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\t\t<table>\n");
        sb.append(r);
        sb.append("\n\t\t</table>");
        return sb.toString();
    }
}
