package pt.isel.ls.view.html;

public class head {

    private title t;
    private style s;

    public head(title t) {
        this.t = t;
    }

    public head(title t, style s) {
        this.t = t;
        this.s = s;
    }

    public head() {
    }

    @Override
    public String toString() {
        return "\t<head>\n"
                + t
                + "\n"
                + s
                + "\n\t</head>";
    }
}
