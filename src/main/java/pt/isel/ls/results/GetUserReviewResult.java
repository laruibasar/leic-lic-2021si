package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.htmlOLD.Html;
import pt.isel.ls.view.htmlOLD.body.Body;
import pt.isel.ls.view.htmlOLD.body.Table;
import pt.isel.ls.view.htmlOLD.head.Head;
import pt.isel.ls.view.htmlOLD.head.Title;

import java.util.ArrayList;
import java.util.List;

public class GetUserReviewResult extends CommandResult {

    private Review review;

    public GetUserReviewResult(List<Model> reviews) {
        if (reviews.size() != 0) {
            this.review = (Review) reviews.get(0);
        }

    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Review Id");
        header.add("Summary");
        header.add("Review");
        header.add("Rating");
        header.add("Movie Id");
        header.add("User Id");

        ArrayList<String[]> rows = new ArrayList<>();
        if (review == null) {
            rows.add(new String[]{ "User review not available" });
        } else {
            rows.add(
                    new String[] {
                            String.valueOf(review.getId()),
                            review.getSummary(),
                            review.getCompleteReview(),
                            String.valueOf(review.getRating()),
                            String.valueOf(review.getMovie()),
                            String.valueOf(review.getMovieCritic())
                    }
            );
        }

        Html h = new Html(
                new Head(
                        new Title("User Review")
                ),
                new Body(
                        new Table(
                                header,
                                rows
                        )
                )
        );
        return h.toString();
    }

    @Override
    public String printPlainText() {
        return review == null ? "User Review not available" : "User Review -> " + review.toString();
    }
}
