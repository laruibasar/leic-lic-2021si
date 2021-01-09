package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Br;
import pt.isel.ls.view.common.Element;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Table;
import pt.isel.ls.view.common.Tbody;
import pt.isel.ls.view.common.Td;
import pt.isel.ls.view.common.Text;
import pt.isel.ls.view.common.Th;
import pt.isel.ls.view.common.Thead;
import pt.isel.ls.view.common.Tr;

import java.util.ArrayList;
import java.util.List;

public class GetMovieAllReviewsResult extends CommandResult {

    private final List<Model> reviews;

    public GetMovieAllReviewsResult(List<Model> reviews) {

        this.reviews = reviews;
    }

    @Override
    public String printHtml() {

        ArrayList<Element> rows = new ArrayList<>();
        Review review;
        Movie movie = ((Review ) reviews.get(0)).getMovie();
        for (Model r: reviews) {
            review = (Review) r;
            rows.add(
                    new Tr(
                            new Td(
                                    new A(
                                            review.getSummary(),
                                            "/movies/" + movie.getMid() + "/reviews/" + review.getId())),
                            new Td(String.valueOf(review.getRating())),
                            new Td(review.getMovieCritic().getName())
                    ));
        }

        pt.isel.ls.view.common.Html h = new Html(
                new Head(
                        new pt.isel.ls.view.common.Title("Movie details")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new Text("List of reviews of movie "),
                        new A(movie.getTitle(),"/movies/" + movie.getMid()),
                        new Table(
                                new Thead(
                                        new Tr(
                                                new Th("Summary"),
                                                new Th("Rating"),
                                                new Th("Movie Critic")
                                        )
                                ),
                                new Tbody(rows)
                        )

                )
        );
        return h.print();
    }

    @Override
    public String printPlainText() {
        StringBuilder sb = new StringBuilder("User All Reviews -> \n");
        for (Model r : reviews) {
            Review review = (Review) r;
            sb.append("ReviewID = "
                    + review.getId()
                    + "\nSummary = "
                    + review.getSummary()
                    + "\nStars ="
                    + review.getRating()
                    + "\nMovieID = "
                    + review.getMovie()
                    + "\nMovieCritic = "
                    + review.getMovieCritic());
            sb.append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public boolean asResult() {
        return !reviews.isEmpty();
    }
}
