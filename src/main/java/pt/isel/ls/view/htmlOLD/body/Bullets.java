package pt.isel.ls.view.htmlOLD.body;

public class Bullets extends Body {

    public String [] bullets;

    public Bullets(String[] bullets) {
        this.bullets = bullets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\t\t<ul>\n");
        for (String b: bullets) {
            sb.append("\t\t\t<li>" + b + "</li>\n");
        }
        sb.append("\t\t</ul>");
        return sb.toString();
    }
}

