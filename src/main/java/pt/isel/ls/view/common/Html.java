package pt.isel.ls.view.common;

public class Html implements Element {
    private Head head;
    private Body body;

    public Html(Head head, Body body) {
        this.head = head;
        this.body = body;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<!DOCTYPE html>\n");
        sb.append("<html>\n");
        sb.append(head.print());
        sb.append(body.print());
        sb.append("</html>");

        return sb.toString();
    }
}
