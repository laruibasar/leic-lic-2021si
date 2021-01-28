package pt.isel.ls.view.common;

public class Td implements Element {
    private String content;

    public Td(String content) {
        this.content = content;
    }

    public Td(Element content) {
        this.content = content.print();
    }

    public Td(Element... contents) {
        StringBuilder aux = new StringBuilder();
        for (Element c : contents) {
            aux.append(c.print());
        }
        this.content = aux.toString();
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<td>");
        sb.append(content);
        sb.append("</td>\n");

        return sb.toString();
    }
}
