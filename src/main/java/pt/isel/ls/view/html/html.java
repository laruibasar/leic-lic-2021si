package pt.isel.ls.view.html;

import pt.isel.ls.view.html.Body.body;
import pt.isel.ls.view.html.Head.head;

public class html {


    private head h;
    private body b;


    public html(head h, body b) {
        this.h = h;
        this.b = b;
    }

    public html(head h) {
        this.h = h;
    }

    public html(body b) {
        this.b = b;
    }

    @Override
    public String toString() {
        if(h == null)
            return "<html>\n"
                    + b
                    + "\n</html>";
        else if (b == null)
            return "<html>\n"
                    + h
                    + "\n</html>";
        else
            return "<html>\n"
                    + h
                    + "\n"
                    + b
                    + "\n</html>";
    }
}
