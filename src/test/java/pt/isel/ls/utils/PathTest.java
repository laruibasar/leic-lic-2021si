package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PathTest {

    @Test
    public void path_equals() {
        Path p1 = new Path("/movies/{mid}/ratings");
        Path p2 = new Path("/movies/150/ratings");
        assertTrue(p1.matches(p2));
    }

    @Test
    public void path_not_equals() {
        Path p1 = new Path("/movies/{mid}/ratings");
        Path p2 = new Path("/movies/{mid}/reviews");
        assertFalse(p1.matches(p2));
    }

    @Test
    public void get_user_id() {
        String userId = "10";
        Path p1 = new Path("/users/10");
        assertEquals(userId, p1.getValue(1));
    }

    @Test
    public void get_movie_id() {
        String movieId = "ls200";
        Path p1 = new Path("/movies/ls200");
        assertEquals(movieId, p1.getValue(1));
    }

    @Test
    public void compare_returnAllReviews_with_returnSpecificReview() {
        Path p1 = new Path("/movies/{mid}/reviews");
        Path p2 = new Path("/movies/{mid}/reviews/{rid}");
        assertFalse(p1.matches(p2));
    }
}
