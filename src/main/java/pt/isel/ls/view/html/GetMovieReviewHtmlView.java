package pt.isel.ls.view.html;

import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.A;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class GetMovieReviewHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();
        html = new Html(
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

        return html.print();
    }
}
