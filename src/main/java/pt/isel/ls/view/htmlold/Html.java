package pt.isel.ls.view.htmlold;

import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.head.Head;

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
        StringBuilder sb = new StringBuilder("<html>\n");
        if (head == null) {
            sb.append(body);
        } else if (body == null) {
            sb.append(head);
        } else {
            sb.append(head
                    + "\n"
                    + body);
        }
        sb.append("\n</html>");
        return sb.toString();
    }
}
