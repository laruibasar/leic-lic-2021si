package pt.isel.ls.view.html;

public class Main {


    public static void main(String[] args) {
        html h = new html(
                    new head(
                        new title("user detail")),
                    new body(
                            new header("User Details")
                    ));
        System.out.println(h.toString());
    }
}
