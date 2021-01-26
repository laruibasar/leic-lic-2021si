package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Movie;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Element;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Table;
import pt.isel.ls.view.common.elements.Tbody;
import pt.isel.ls.view.common.elements.Td;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Th;
import pt.isel.ls.view.common.elements.Thead;
import pt.isel.ls.view.common.elements.Tr;
import pt.isel.ls.view.common.elements.Title;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetMovieAllReviewsResult extends CommandResult {

    private List<Model> reviews = new LinkedList<>();

    public GetMovieAllReviewsResult() {

    }

    public GetMovieAllReviewsResult(List<Model> reviews) {

        this.reviews = reviews;
    }

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

        Html h = new Html(
                new Head(
                        new Title("Movie details")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new Br(),
                        new Text("List of reviews of movie "),
                        new A(movie.getTitle(),"/movies/" + movie.getMid()),
                        new Br(),
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

    public String printPlainText() {
        StringBuilder sb = new StringBuilder("User All Reviews -> \n");
        for (Model r : reviews) {
            Review review = (Review) r;
            sb.append("ReviewID = ").append(review.getId());
            sb.append("\nSummary = ").append(review.getSummary());
            sb.append("\nStars =").append(review.getRating());
            sb.append("\nMovieID = ").append(review.getMovie());
            sb.append("\nMovieCritic = ").append(review.getMovieCritic());
            sb.append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public boolean asResult() {
        return !reviews.isEmpty();
    }

    @Override
    public Object getResult() {
        return reviews;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
