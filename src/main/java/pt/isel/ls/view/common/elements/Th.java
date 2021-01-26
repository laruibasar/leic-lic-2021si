package pt.isel.ls.view.common.elements;

public class Th implements Element {
    private String content;

    public Th(String content) {
        this.content = content;
    }

    public Th(Element content) {
        this.content = content.print();
    }

    public Th(Element... contents) {
        StringBuilder aux = new StringBuilder();
        for (Element c : contents) {
            aux.append(c.print());
        }
        this.content = aux.toString();
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<th>");
        sb.append(content);
        sb.append("</th>\n");

        return sb.toString();
    }
}
