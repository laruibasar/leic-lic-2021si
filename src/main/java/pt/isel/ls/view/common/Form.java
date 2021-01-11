package pt.isel.ls.view.common;

import pt.isel.ls.utils.Method;

import java.util.ArrayList;
import java.util.Arrays;

public class Form implements Element {
    private ArrayList<Element> content = new ArrayList<>();
    private Method method;
    private String uri;

    public Form(Method method, String uri, Element... elements) {
        this.method = method;
        this.uri = uri;
        content.addAll(Arrays.asList(elements));
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<form");
        sb.append(" action=\"").append(uri).append("\"");
        sb.append(" method=\"").append(method.toString()).append("\">\n");

        for (Element element : content) {
            sb.append(element.print());
        }

        sb.append("</form>\n");
        return sb.toString();
    }
}
