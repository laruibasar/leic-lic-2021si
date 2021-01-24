package pt.isel.ls.view.common.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class Ul implements Element {
    private ArrayList<Li> content = new ArrayList<>();

    public Ul(Li... elements) {
        content.addAll(Arrays.asList(elements));
    }

    public Ul(ArrayList<Li> elements) {
        content = elements;
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
