package pt.isel.ls.view.html.Body;

public class bullets extends body {

    public String [] items;

    public bullets(String[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<ul>\n");
        for(String i: items) {
            sb.append("\t\t\t<li>" + i + "</li>\n");
        }
        sb.append("\t\t</ul>");
        return sb.toString();
    }
}
