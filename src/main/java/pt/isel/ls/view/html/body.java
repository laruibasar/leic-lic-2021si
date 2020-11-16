package pt.isel.ls.view.html;

public class body {

    private header hd;

    public body(header hd) {
        this.hd = hd;
    }

    public body() {
    }

    @Override
    public String toString() {
        return "\t<body>\n"
                + hd
                + "\n\t</body>";
    }
}
