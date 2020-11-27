package pt.isel.ls.view.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.model.User;
import pt.isel.ls.view.html.Html;
import pt.isel.ls.view.html.body.Body;
import pt.isel.ls.view.html.body.Table;
import pt.isel.ls.view.html.head.Head;
import pt.isel.ls.view.html.head.Title;

import java.util.ArrayList;
import java.util.List;

public class CreateMovieReviewResult extends CommandResult {
    private Review review;

    public CreateMovieReviewResult(List<Model> review) {
        if(review.size() != 1 || !(review instanceof User)){
            //create exception
        }

        this.review = (Review) review.get(0);
    }

    @Override
    public String printHTML() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(
                new String[] {
                        "" + review.getId(),
                        "" + review.getSummary(),
                        "" + review.getCompleteReview(),
                        "" + review.getMovie(),
                        "" + review.getMovieCritic(),
                        "" + review.getRating()
                }
        );
        Html h = new Html(
                new Head(
                        new Title("Created Review")
                ),
                new Body(
                        new Table(
                                rows
                        )
                )

        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        return "Created Review -> " +
                review.toString();
    }
}
