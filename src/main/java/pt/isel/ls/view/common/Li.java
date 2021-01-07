package pt.isel.ls.view.common;

public class Li implements Element {
    private String content;

    public Li(String content) {
        this.content = content;
    }

    public Li(Element content) {
        this.content = content.print();
    }

    public Li(Element... contents) {
        StringBuilder aux = new StringBuilder();
        for (Element c : contents) {
            aux.append(c.print());
        }
        this.content = aux.toString();
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<li>");
        sb.append(content);
        sb.append("</li>\n");

        return sb.toString();
    }
}
