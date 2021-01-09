package pt.isel.ls.view.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Tbody implements Element {
    public ArrayList<Element> content = new ArrayList<>();

    public Tbody(Element... elements) {
        content.addAll(Arrays.asList(elements));
    }

    public Tbody(ArrayList<Element> content) {
        this.content = content;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<tbody>\n");
        for (Element element : content) {
            sb.append(element.print());
        }
        sb.append("</tbody>\n");

        return sb.toString();
    }
}
