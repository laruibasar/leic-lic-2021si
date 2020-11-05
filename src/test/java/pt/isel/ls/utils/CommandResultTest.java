package pt.isel.ls.utils;

import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.services.exceptions.InvalidAverageException;

public class CommandResultTest {

    @Test
    public void get_review_with_id() throws InvalidAverageException {
        //String de uma review
        //assertEquals(r1.equals(System.setOut(new PrintStream(outputStreamCaptor)));
        String[] aux = new String[]{"GET /movies/{1}/reviews/{1234}"};
        final String r1 = "Stars = " + "4"
                + "\nSummary = " + "Edge of Your Seat Fun!"
                + "\nMovie Critic = " + "1"
                + "\n\nComplete Review = "
                + "Great Story! Great Writing! Great Acting! Great Directing! This movie has it all."
                + "\nMovieID = " + "1"
                + "\nReviewID = " + "1234";
        App.main(aux);
    }
}