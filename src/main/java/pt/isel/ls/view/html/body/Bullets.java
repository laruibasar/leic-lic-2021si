package pt.isel.ls.view.html.body;

public class Bullets extends Body {

    public String [] bullets;

    public Bullets(String[] bullets) {
        this.bullets = bullets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<ul>\n");
        for (String i: items) {
            sb.append("\t\t\t<li>" + i + "</li>\n");
        }
        sb.append("\t\t</ul>");
        return sb.toString();
    }
}
