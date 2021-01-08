package pt.isel.ls.view.htmlold.head;

public class Head {

    private Title title;

    public Head(Title t) {
        this.title = t;
    }

    public Head() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\t<head>\n");
        if (title != null) {
            sb.append(title
                    + "\n");
        }
        sb.append("\t</head>");
        return sb.toString();
    }
}
