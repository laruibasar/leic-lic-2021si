package pt.isel.ls.view.html;

public class title extends head{

    private String title;

    public title(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "\t\t<h1>"
                +title
                +"</h1>";
    }
}
