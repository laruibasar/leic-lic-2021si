package pt.isel.ls.view.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Table implements Element {
    private ArrayList<Element> content = new ArrayList<>();

    public Table(Tr... rows) {
        content.addAll(Arrays.asList(rows));
    }

    public Table(Thead thead, Tbody... bodies) {
        content.add(thead);
        content.addAll(Arrays.asList(bodies));
    }

    public Table(Tbody... bodies) {
        content.addAll(Arrays.asList(bodies));
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<table>\n");
        for (Element element : content) {
            sb.append(element.print());
        }
        sb.append("</table>\n");

        return sb.toString();
    }
}
