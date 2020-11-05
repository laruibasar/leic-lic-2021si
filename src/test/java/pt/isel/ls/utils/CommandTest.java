package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class CommandTest {

    //testes ao create movie têm que ter em atenção os campos a NULL

    @Test
    public void command_equals() {
        Command template = new Command(Method.GET, new Path("/movies/{mid}/ratings"));
        Command input = new Command(Method.GET, new Path("/movies/150/ratings"));
        assertTrue(input.matches(template));
    }

    @Test
    public void command_not_equals() {
        Command template = new Command(Method.GET, new Path("/movies/{mid}/ratings"));
        Command input = new Command(Method.GET, new Path("/users/1"));
        assertFalse(input.matches(template));
    }

    @Test
    public void command_to_string() {
        Command cmd = new Command(Method.GET, new Path("/movies/{mid}/ratings"));
        assertEquals("GET /movies/{mid}/ratings", cmd.toString());
    }
}
