package pt.isel.ls.view.common;

public class Textarea implements Element {
    private String content;
    private String name;
    private int rows;
    private int cols;

    public Textarea(String name) {
        this(name, "", 5, 33);
    }

    public Textarea(String name, String content) {
        this(name, content, 5, 33);
    }

    public Textarea(String name, String content, int rows, int cols) {
        this.content = content;
        this.name = name;
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<textarea");
        sb.append(" id=\"").append(name).append("\"");
        sb.append(" name=\"").append(name).append("\"");
        sb.append(" rows=\"").append(rows).append("\"");
        sb.append(" cols=\"").append(cols).append("\"");
        sb.append(">\n");

        sb.append(content).append("\n");

        sb.append("</textarea>\n");

        return sb.toString();
    }
}
