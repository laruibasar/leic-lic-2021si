package pt.isel.ls.handlers;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.mockdata.MockDataTransaction;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

import static org.junit.Assert.assertEquals;

public class CreateMovieReviewTest {

    @Test
    public void insert_review_correct() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("uid=1&reviewSummary=O+melhor+do+género&rating=5"
                + "&review=p");
        Command cmd = new Command(Method.POST, new Path("/movies/1/reviews"), params);
        cmd.setTemplate(new Command(Method.POST, new Path("/movies/{mid}/reviews")));

        CreateMovieReviewHandler handler = new CreateMovieReviewHandler();
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult rs = handler.execute(cmd);

        /*assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            Review testMovie = (Review) test;
            assertEquals("O melhor do género", testMovie.getSummary());
            assertEquals(5, testMovie.getRating());
            assertEquals(1, testMovie.getMovie());
        }*/
    }

    @Test (expected = HandlerException.class)
    public void insert_misspell_parameter() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("iud=1&reviewSummary=O+pior+do+género&rating=1"
                + "&review=O+filme+que+marca+o+género+histórico-drama,+"
                + "pela+negativa!+Um+embelezamento+cheio+de+erros+de+cenário");
        Command cmd = new Command(Method.POST, new Path("/movies/1/reviews"), params);
        CreateMovieReviewHandler handler = new CreateMovieReviewHandler();
        handler.setDataTransaction(new MockDataTransaction());
        CommandResult rs = handler.execute(cmd);
    }
}
