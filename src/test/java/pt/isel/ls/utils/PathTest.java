package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PathTest {
    @Test
    public void path_equals() {
        Path p1 = new Path("/movies/{mid}/ratings");
        Path p2 = new Path("/movies/150/ratings");
        assertTrue(p1.equals(p2));
    }

    @Test
    public void path_not_equals() {
        Path p1 = new Path("/movies/{mid}/ratings");
        Path p2 = new Path("/movies/{mid}/reviews");
        assertFalse(p1.equals(p2));
    }
}
