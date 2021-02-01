package pt.isel.ls.view.html;

import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Br;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class CreateMovieReviewHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();
        html = new Html(
                new Head(
                        new Title("Created review")
                ),
                new Body(
                        new Br(),
                        new Br(),
                        new Ul(
                                new Li(new Text("Rid: " + review.getMovieCritic().getId())),
                                new Li(new Text("Rate: " + review.getRating())),
                                new Li(new Text("Summary: " + review.getSummary())),
                                new Li(new Text("Complete Review: " + review.getCompleteReview()))
                        )
                )
        );
        return html.print();
    }
}
