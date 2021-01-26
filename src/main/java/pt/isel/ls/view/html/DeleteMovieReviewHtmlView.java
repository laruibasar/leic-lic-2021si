package pt.isel.ls.view.html;

import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.elements.Body;
import pt.isel.ls.view.common.elements.Head;
import pt.isel.ls.view.common.elements.Html;
import pt.isel.ls.view.common.elements.Li;
import pt.isel.ls.view.common.elements.Text;
import pt.isel.ls.view.common.elements.Title;
import pt.isel.ls.view.common.elements.Ul;

public class DeleteMovieReviewHtmlView extends HtmlView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();
        html = new Html(
                new Head(
                        new Title("Deleted review")
                ),
                new Body(
                        new Ul(
                                new Li(new Text("Review id: " + review.getId())),
                                new Li(new Text("Movie Critic: " + review.getMovieCritic().getName())),
                                new Li(new Text("Rate " + review.getRating())),
                                new Li(new Text("Summary: " + review.getSummary())),
                                new Li(new Text("Complete review: " + review.getCompleteReview()))
                        )
                )
        );
        return html.print();
    }
}
