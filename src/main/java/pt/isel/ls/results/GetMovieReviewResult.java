package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.common.A;
import pt.isel.ls.view.common.Body;
import pt.isel.ls.view.common.Head;
import pt.isel.ls.view.common.Html;
import pt.isel.ls.view.common.Li;
import pt.isel.ls.view.common.Text;
import pt.isel.ls.view.common.Title;
import pt.isel.ls.view.common.Ul;

import java.util.List;

public class GetMovieReviewResult extends CommandResult {

    private Review review;

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
                        new A("Back", "http://localhost/users/" + review.getMovieCritic().getId()),
                        new A(review.getMovie().getTitle() + " " + review.getMovie().getYear(), "http://localhost/movies/"
                                + review.getMovie().getMid()),
                        new A("All reviews", "http://localhost/movies/"
                                + review.getMovie().getMid() + "/reviews/"
                                + review.getRid()),
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
        return review == null ? "Review not available" : "Movie Review -> " + review.toString();
    }
}
