package pt.isel.ls.view.common.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Div implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Div(Element... elements) {
        content.addAll(Arrays.asList(elements));
    }

    @Override
    public String print() {
        //Default
        StringBuilder sb = new StringBuilder("<div>");

        for (Element element : content) {
            sb.append(element.print());
        }

        sb.append("</div>\n");
        return sb.toString();
    }
}
