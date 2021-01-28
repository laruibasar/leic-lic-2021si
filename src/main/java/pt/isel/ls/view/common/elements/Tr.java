package pt.isel.ls.view.common.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Tr implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Tr(Element... elements) {
        content.addAll(Arrays.asList(elements));
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
