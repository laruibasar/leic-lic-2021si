package pt.isel.ls.view.html;

import pt.isel.ls.view.html.Body.body;
import pt.isel.ls.view.html.Body.bullets;
import pt.isel.ls.view.html.Body.row;
import pt.isel.ls.view.html.Body.table;
import pt.isel.ls.view.html.Head.head;
import pt.isel.ls.view.html.Head.title;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"ID", "REVIEW", "RATING"});
        rows.add(new String[]{"1", "Horrible", "0"});
        html h = new html(
                    new head(
                        new title("user detail")
                    ),
                    new body(
                            new table(rows)
                    )
        );

        html h1 = new html(new body(

        )
        );

        html h2 = new html(
                new head(
                        new title("User detail")
                ),
                new body(
                        new bullets(
                                new String[] {"test1", "test2", "test2"}
                        )
                )
        );
        System.out.println(h.toString());
//        System.out.println(h2.toString());
//        System.out.println(h2.toString());
    }
}
