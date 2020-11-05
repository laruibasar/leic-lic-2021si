package pt.isel.ls.utils;

import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.services.exceptions.InvalidAverageException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class CommandResultTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void get_review_with_id() throws InvalidAverageException {
        String[] aux = new String[]{"GET /movies/{1}/reviews/{1234}"};
        final String r1 = "Stars = " + "4"
                + "\nSummary = " + "Edge of Your Seat Fun!"
                + "\nMovie Critic = " + "1"
                + "\n\nComplete Review = "
                + "Great Story! Great Writing! Great Acting! Great Directing! This movie has it all."
                + "\nMovieID = " + "1"
                + "\nReviewID = " + "1234";
        App.main(aux);
        assertEquals(r1, outputStreamCaptor.toString()
                .trim());
    }
}