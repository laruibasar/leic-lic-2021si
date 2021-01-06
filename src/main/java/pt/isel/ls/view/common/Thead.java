package pt.isel.ls.view.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Thead implements Element {
    public ArrayList<Element> content = new ArrayList<>();

    public Thead(Tr... rows) {
        content.addAll(Arrays.asList(rows));
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<thead>\n");
        for (Element element : content) {
            sb.append(element.print());
        }
        sb.append("</thead>\n");

        return sb.toString();
    }
}
