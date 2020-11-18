package pt.isel.ls.view.html;

public class style extends head {

    private String border = "";
    private String border_collapse = "";

    public style(String border, String border_collapse) {
        this.border_collapse = border_collapse;
        this.border = border;
    }

    public style(String border) {
        this.border = border;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                "\t<style>"
                + "\n\ttable, th, td {"
                + "\n\t\t"
                + border
                + "\n");
        if(border_collapse.isEmpty())
                sb.append("\t}\n"
                        + "\t</style>");
        else
            sb.append("\t\t"
                    + border_collapse
                    + "\n"
                    + "\t}\n"
                    + "\t</style>");
        return sb.toString();
    }
}
