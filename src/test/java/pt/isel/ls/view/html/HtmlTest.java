package pt.isel.ls.view.html;

import org.junit.Test;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Bullets;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


public class HtmlTest {

    @Test
    public void create_table_with_row() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[] {"Genre", "year", "Rating", "Review"});
        Html h = new Html(
                new Body(
                        new Table(
                                rows
                        )
                )
        );
        String comp = "<html>\n"
                + "\t<body>\n"
                + "\t\t<table>\n"
                + "\t\t\t<tr>\n"
                + "\t\t\t\t<td>Genre</td>\n"
                + "\t\t\t\t<td>year</td>\n"
                + "\t\t\t\t<td>Rating</td>\n"
                + "\t\t\t\t<td>Review</td>\n"
                + "\t\t\t</tr>\n"
                + "\t\t</table>\n"
                + "\t</body>\n"
                + "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_bullet() {
        Html h = new Html(
                new Body(
                        new Bullets(new String[]{"test1", "test2", "test3"})
                )
        );
        final String comp = "<html>\n"
                + "\t<body>\n"
                + "\t\t<ul>\n"
                + "\t\t\t<li>test1</li>\n"
                + "\t\t\t<li>test2</li>\n"
                + "\t\t\t<li>test3</li>\n"
                + "\t\t</ul>\n"
                + "\t</body>\n"
                + "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_title() {
        Html h = new Html(
                new Head(
                        new Title("User details")
                )
        );
        final String comp = "<html>\n"
                + "\t<head>\n"
                + "\t\t<title>User details</title>\n"
                + "\t</head>\n"
                + "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_head() {
        Html h = new Html(
                new Head()
        );
        final String comp = "<html>\n"
                + "\t<head>\n"
                + "\t</head>\n"
                + "</html>";
        assertEquals(comp, h.toString());
    }

    @Test
    public void create_body() {
        Html h = new Html(
                new Body()
        );
        final String comp = "<html>\n"
                + "\t<body>\n"
                + "\t</body>\n"
                + "</html>";
        assertEquals(comp, h.toString());
    }
}