package pt.isel.ls.view.html;

import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.head.Head;

public class Html {


    private Head head;
    private Body body;


    public Html(Head h, Body b) {
        this.head = h;
        this.body = b;
    }

    public Html(Head h) {
        this.head = h;
    }

    public Html(Body b) {
        this.body = b;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "<html>\n"
                    + body
                    + "\n</html>";
        } else if (body == null) {
            return "<html>\n"
                    + head
                    + "\n</html>";
        } else {
            return "<html>\n"
                    + head
                    + "\n"
                    + body
                    + "\n</html>";
        }
    }
}
