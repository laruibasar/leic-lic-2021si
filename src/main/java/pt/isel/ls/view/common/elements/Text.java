package pt.isel.ls.view.common.elements;

public class Text implements Element {
    private String content;

    public Text(String content) {
        this.content = content;
    }

    @Override
    public String print() {
        return content;
    }
}
