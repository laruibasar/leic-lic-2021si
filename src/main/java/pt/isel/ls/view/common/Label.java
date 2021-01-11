package pt.isel.ls.view.common;

public class Label implements Element {
    private String content;
    private String forInput;

    public Label(String content) {
        this.content = content;
    }

    public Label(String content, String input) {
        this.content = content;
        this.forInput = input;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<label");
        if (forInput != null) {
            sb.append(" for=\"").append(forInput).append("\"");
        }
        sb.append(">");

        sb.append(content);
        sb.append("</label>\n");

        return sb.toString();
    }
}
