package pt.isel.ls.view.html;

import org.junit.jupiter.api.Test;
import pt.isel.ls.view.html.Body.body;
import pt.isel.ls.view.html.Body.row;
import pt.isel.ls.view.html.Body.table;

import static org.junit.Assert.*;

public class htmlTest {

    @Test
    public void create_table_with_row() {
        html h = new html(
                new body(
                        new table(
                                new row(
                                        new String[] {"Genre", "year", "Rating", "Review"}
                                )
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

}