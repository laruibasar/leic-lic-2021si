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


public class GetUserReviewHtmlView extends HtmlView implements IView {

    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();

        html = new Html(
                new Head(
                        new Title("User Review details")
                ),
                new Body(
                        new A("Return home","/"),
                        new Br(),
                        new Ul(new Li(new Text("Review Id: " + review.getId())),
                                new Li(new Text("Summary: " + review.getSummary())),
                                new Li(new Text("Stars: " + review.getRating())),
                                new Li(new Text("Review: " + review.getCompleteReview())),
                                new Li(new A("Stars: " + review.getMovie(),"/movies/" + review.getMovie())),
                                new Li(new Text("Reviewer: " + review.getMovieCritic()))
                        )
                )
        );

        return html.print();
    }
}
