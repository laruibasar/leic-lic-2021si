package pt.isel.ls.view.html.body;

public class Body {

    private Table table;
    private Bullets bullets;


    public Body(Table tb) {
        this.table = tb;
    }

    public Body(Bullets b) {
        this.bullets = b;
    }

    public Body() {
    }

    @Override
    public String toString() {
        if (table != null) {
            return "\t<body>\n"
                    + table
                    + "\n\t</body>";
        } else if (bullets != null) {
            return "\t<body>\n"
                    + bullets
                    + "\n\t</body>";
        } else {
            return "\t<body>\n"
                    + "\t</body>";
        }

    }
}
