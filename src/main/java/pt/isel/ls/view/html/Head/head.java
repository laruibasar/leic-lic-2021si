package pt.isel.ls.view.html.Head;

public class head {

    private title t;

    public head(title t) {
        this.t = t;
    }

    public head() {
    }

    @Override
    public String toString() {
        if(t == null) {
            return "\t<head>"
                    + "\n" +
                    "\t</head>";
        }
        return "\t<head>\n"
                + t
                + "\n\t</head>";
    }
}
