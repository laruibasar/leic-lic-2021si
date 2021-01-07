package pt.isel.ls.view.common;

import java.util.ArrayList;

public class Tr implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Tr(Element... elements) {
        for (Element element : elements) {
            content.add(element);
        }
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<tr>");
        for (Element element : content) {
            sb.append(element.print());
        }
        sb.append("</tr>\n");

        return sb.toString();
    }
}
