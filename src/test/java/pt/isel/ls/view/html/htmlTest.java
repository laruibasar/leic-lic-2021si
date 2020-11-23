package pt.isel.ls.view.html;

import org.junit.Test;
import pt.isel.ls.view.html.Body.body;
import pt.isel.ls.view.html.Body.bullets;
import pt.isel.ls.view.html.Body.row;
import pt.isel.ls.view.html.Body.table;
import pt.isel.ls.view.html.Head.head;
import pt.isel.ls.view.html.Head.title;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class htmlTest {

    @Test
    public void create_table_with_row() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[] {"Genre", "year", "Rating", "Review"});
        html h = new html(
                new body(
                        new table(
                                rows
                        )
                )
        );
        String comp = "<html>\n" +
                "\t<body>\n" +
                "\t\t<table>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>Genre</td>\n" +
                "\t\t\t\t<td>year</td>\n" +
                "\t\t\t\t<td>Rating</td>\n" +
                "\t\t\t\t<td>Review</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_bullet() {
        html h = new html(
                new body(
                        new bullets(new String[]{"test1", "test2", "test3"})
                )
        );
        final String comp = "<html>\n" +
                "\t<body>\n" +
                "\t\t<ul>\n" +
                "\t\t\t<li>test1</li>\n" +
                "\t\t\t<li>test2</li>\n" +
                "\t\t\t<li>test3</li>\n" +
                "\t\t</ul>\n" +
                "\t</body>\n" +
                "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_title() {
        html h = new html(
                new head(
                        new title("User details")
                )
        );
        final String comp = "<html>\n" +
                "\t<head>\n" +
                "\t\t<h1>User details</h1>\n" +
                "\t</head>\n" +
                "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_head() {
        html h = new html(
                new head()
        );
        final String comp = "<html>\n" +
                "\t<head>\n" +
                "\t</head>\n" +
                "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_body() {
        html h = new html(
                new body()
        );
        final String comp = "<html>\n" +
                "\t<body>\n" +
                "\t</body>\n" +
                "</html>";
        assertEquals(comp, h.toString());
    }
}