package pt.isel.ls.view.html;

public class html {


    private head h;
    private body b;


    public html(head h, body b) {
        this.h = h;
        this.b = b;
    }

    @Override
    public String toString() {
        return "<html>\n"
                + h
                + "\n"
                + b
                + "\n</html>";
    }
}
