package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

import java.util.List;

public class GetMovieReviewResult extends CommandResult {

    private Review review;

    public GetMovieReviewResult() {

    }

    public GetMovieReviewResult(List<Model> reviews) {
        if (reviews.size() != 0) {
            this.review = (Review) reviews.get(0);
        }

    }

    @Override
    public String printHtml() {
        Html h = new Html(
                new Head(
                        new Title("User Review")
                ),
                new Body(
                        new A("Return home", "/"),
                        new Br(),
                        new A("Back", "/users/" + review.getMovieCritic().getId()),
                        new Br(),
                        new Br(),
                        new Br(),
                        new A(review.getMovie().getTitle() + " " + review.getMovie().getYear(), "/movies/"
                                + review.getMovie().getMid()),
                        new Br(),
                        new A(review.getMovie().getTitle() + " Reviews", "/movies/"
                                + review.getMovie().getMid() + "/reviews"),
                        new Br(),
                        new Ul(
                                new Li(new Text(String.valueOf(review.getMovieCritic().getName()))),
                                new Li(new Text("Rate " + review.getRating())),
                                new Li(new Text(review.getSummary())),
                                new Li(new Text(review.getCompleteReview()))
                        )
                )
        );
        return h.print();
    }

    @Override
    public String printPlainText() {
        return review == null ? "Review not available" : "Movie Review: "
                + "\nCritic      = " + review.getMovieCritic().getName()
                + "\nMovieID     = " + review.getMovie().getMid()
                + "\nUserID      = " + review.getMovieCritic().getId()
                + "\n\nScore       = " + review.getRating()
                + "\nSummary     = " + review.getSummary()
                + "\nFull review = " + review.getCompleteReview();
    }

    @Override
    public boolean asResult() {
        return review != null;
    }

    @Override
    public Object getResult() {
        return review;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
