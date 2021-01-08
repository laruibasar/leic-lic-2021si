package pt.isel.ls.view.common;

import java.util.ArrayList;

public class Tbody implements Element {
    public ArrayList<Element> content = new ArrayList<>();

    public Tbody(ArrayList<Element> rows) {
        this.content = rows;
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
