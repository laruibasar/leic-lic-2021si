package pt.isel.ls.view.common.elements;

public class Title implements Element {
    private String content;

    public Title(String content) {
        this.content = content;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder("<title>");
        sb.append(content);
        sb.append("</title>\n");

        return sb.toString();
    }
}
