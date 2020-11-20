package pt.isel.ls.view.html;

import pt.isel.ls.view.html.Body.body;
import pt.isel.ls.view.html.Body.bullets;
import pt.isel.ls.view.html.Body.header;
import pt.isel.ls.view.html.Body.row;
import pt.isel.ls.view.html.Body.table;
import pt.isel.ls.view.html.Head.head;
import pt.isel.ls.view.html.Head.title;

public class Main {


    public static void main(String[] args) {
        html h = new html(
                    new head(
                        new title("user detail")
                    ),
                    new body(
                            new table(
                                new row(
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

        html h2 = new html(
                new body(
                        new bullets(
                                new String[] {"test1", "test2", "test2"}
                        )
                )
        );
        System.out.println(h.toString());
        System.out.println(h1.toString());
        System.out.println(h2.toString());
    }
}
