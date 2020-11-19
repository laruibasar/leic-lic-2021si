package pt.isel.ls.services;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.IMovieReviewData;
import pt.isel.ls.mockdata.MockMovieReviewData;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.ParametersExceptions;
import pt.isel.ls.utils.Path;

import static org.junit.Assert.assertEquals;

public class CreateMovieReviewTest {

    @Test
    public void insert_review_correct() throws HandlerException {
        AppConfig.setup();
        IMovieReviewData reviewData = new MockMovieReviewData();

        Parameters params = new Parameters();
        params.setValues("uid=1&reviewSummary=O+melhor+do+género&rating=5"
                + "&review=O+filme+que+marca+o+género+histórico-drama,+"
                + "com+Russel+Crowe+num+brilhante+papel+como+general/gladiador!");
        Command cmd = new Command(Method.POST, new Path("/movies/1/reviews"), params);
        CreateMovieReviewHandler handler = new CreateMovieReviewHandler();
        handler.setReviewDataConnection(reviewData);
        CommandResult rs = handler.execute(cmd);

        assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            Review testMovie = (Review) test;
            assertEquals("O melhor do género", testMovie.getSummary());
            assertEquals(5, testMovie.getRating());
            assertEquals(1, testMovie.getMovie());
        }
    }

    @Test (expected = ParametersExceptions.class)
    public void insert_misspell_parameter() throws HandlerException {
        AppConfig.setup();
        IMovieReviewData reviewData = new MockMovieReviewData();

        Parameters params = new Parameters();
        params.setValues("iud=1&reviewSummary=O+pior+do+género&rating=1"
                + "&review=O+filme+que+marca+o+género+histórico-drama,+"
                + "pela+negativa!+Um+embelezamento+cheio+de+erros+de+cenário");
        Command cmd = new Command(Method.POST, new Path("/movies/1/reviews"), params);
        CreateMovieReviewHandler handler = new CreateMovieReviewHandler();
        handler.setReviewDataConnection(reviewData);
        CommandResult rs = handler.execute(cmd); // expect fail here
    }
}
