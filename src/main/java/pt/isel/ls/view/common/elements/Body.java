package pt.isel.ls.view.common.elements;

import java.util.ArrayList;

public class Body implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Body(Element... elements) {
        for (Element element : elements) {
            content.add(element);
        }
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
