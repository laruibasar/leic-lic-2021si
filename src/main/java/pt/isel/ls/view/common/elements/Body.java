package pt.isel.ls.view.common.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Body implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Body(Element... elements) {
        content.addAll(Arrays.asList(elements));
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<body>\n");
        for (Element element : content) {
            sb.append(element.print());
        }
        sb.append("</body>\n");

        return sb.toString();
    }
}
