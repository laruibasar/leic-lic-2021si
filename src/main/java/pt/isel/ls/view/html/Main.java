package pt.isel.ls.view.html;

public class Main {


    public static void main(String[] args) {
        html h = new html(
                    new head(
                        new title("user detail"),
                        new style("border: 1px solid black;", "border-collapse: collapse;")
                    ),
                    new body(
                            new header("User Details"),
                            new table(
                                new row (
                                        new String[]{"userID", "name"}
                                )
                            )
                    )
        );

        html h1 = new html(new body(
                new table(
                        new row(
                                new String[]{"movieID", "rating", "crappy movie", "2012"}
                        )
                )
        )
        );
        System.out.println(h.toString());
        System.out.println(h1.toString());
    }
}
