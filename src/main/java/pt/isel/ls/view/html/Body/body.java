package pt.isel.ls.view.html.Body;

public class body {

    private table tb;
    private bullets b;


    public body(table tb) {
        this.tb = tb;
    }

    public body(bullets b) {
        this.b = b;
    }

    public body() {
    }

    @Override
    public String toString() {
        if(b == null) {
            return "\t<body>\n"
                    + tb
                    + "\n\t</body>";
        }
        return "\t<body>\n"
                + b
                + "\n\t</body>";
    }
}
