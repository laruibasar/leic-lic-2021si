package pt.isel.ls.view.htmlOLD.body;

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
        StringBuilder sb = new StringBuilder("\t<body>\n");
        if (table != null) {
            sb.append(table
                    + "\n");
        } else if (bullets != null) {
            sb.append(bullets
                    + "\n");
        }
        sb.append("\t</body>");
        return sb.toString();
    }
}
