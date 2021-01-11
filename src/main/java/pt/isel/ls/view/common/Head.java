package pt.isel.ls.view.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Head implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Head(Element... elements) {
        content.addAll(Arrays.asList(elements));
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
