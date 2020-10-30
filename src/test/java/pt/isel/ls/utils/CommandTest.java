package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class CommandTest {

    @Test
    public void command_equals() {
        Command template = new Command(Method.GET, "/movies/{mid}/ratings");
        Command input = new Command(Method.GET, "/movies/150/ratings");
        assertTrue(input.equals(template));
    }

    @Test
    public void command_not_equals() {
        Command template = new Command(Method.GET, "/movies/{mid}/ratings");
        Command input = new Command(Method.GET, "/users/1");
        assertFalse(input.equals(template));
    }

    @Test
    public void command_to_string() {
        Command cmd = new Command(Method.GET, "/movies/{mid}/ratings");
        assertEquals("get /movies/{mid}/ratings", cmd.toString());
    }
}
