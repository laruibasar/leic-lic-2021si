package pt.isel.ls.view.common.elements;

public class Input implements Element {
    private String type;
    private String name;
    private String config;

    public Input(String type, String name) {
        this(type, name, "");
    }

    public Input(String type, String name, String config) {
        this.type = type;
        this.name = name;
        this.config = config;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<input");
        sb.append(" type=\"").append(type).append("\"");
        sb.append(" id=\"").append(name).append("\"");
        sb.append(" name=\"").append(name).append("\"");
        sb.append(" ").append(config).append(" ");
        sb.append(">\n");

        return sb.toString();
    }
}
