package pt.isel.ls.view.common.elements;

import java.util.ArrayList;

public class Ul implements Element {
    private ArrayList<Li> content = new ArrayList<>();

    public Ul(Li... elements) {
        for (Li element : elements) {
            this.content.add(element);
        }
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<ul>\n");
        for (Li li : content) {
            sb.append(li.print());
        }
        sb.append("</ul>\n");

        return sb.toString();
    }
}
