package pt.isel.ls.view.html.head;

public class Title extends Head {

    private String title;

    public Title(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "\t\t<title>"
                + title
                + "</title>";
    }
}
