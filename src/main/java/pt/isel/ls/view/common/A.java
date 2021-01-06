package pt.isel.ls.view.common;

public class A implements Element {
    private final String tag = "a";
    private String content = "";
    private String href = "";

    public A(String content) {
        this(content, "");
    }

    public A(String content, String href) {
        this.content = content;
        this.href = href;
    }

    public A(Element child, String href) {
        this.content = child.print();
        this.href = href;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append("<")
                .append(tag).append(" ")
                .append("href=\"")
                .append(href)
                .append("\"")
                .append(">");
        sb.append(content);
        sb.append("</").append(tag).append(">\n");

        return sb.toString();
    }
}
