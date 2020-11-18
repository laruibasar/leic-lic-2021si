package pt.isel.ls.view.html;

public class body {

    private header hd;
    private table tb;

    public body(header hd) {
        this.hd = hd;
    }

    public body(header hd, table tb) {
        this.hd = hd;
        this.tb = tb;
    }

    public body() {
    }

    @Override
    public String toString() {
        return "\t<body>\n"
                + hd
                + tb
                + "\n\t</body>";
    }
}
