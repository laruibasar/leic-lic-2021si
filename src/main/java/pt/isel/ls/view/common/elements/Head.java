package pt.isel.ls.view.common.elements;

import java.util.ArrayList;

public class Head implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Head(Element... elements) {
        for (Element element : elements) {
            content.add(element);
        }
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<head>\n");
        for (Element element : content) {
            sb.append(element.print());
        }
        sb.append("</head>\n");

        return sb.toString();
    }
}
