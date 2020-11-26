package pt.isel.ls.view.html.head;

public class Head {

    private Title title;

    public Head(Title t) {
        this.title = t;
    }

    public Head() {
    }

    @Override
    public String toString() {
        if (title == null) {
            return "\t<head>"
                    + "\n"
                    + "\t</head>";
        }
        return "\t<head>\n"
                + title
                + "\n\t</head>";
    }
}
