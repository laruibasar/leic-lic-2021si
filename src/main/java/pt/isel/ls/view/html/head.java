package pt.isel.ls.view.html;

public class head {

    private title t;

    public head(title title) {
        t = title;
    }

    public head() {
    }

    @Override
    public String toString() {
        return "\t<head>\n"
                + t
                + "\n\t</head>";
    }
}
