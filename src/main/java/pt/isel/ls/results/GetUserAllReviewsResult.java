package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.view.htmlold.Html;
import pt.isel.ls.view.htmlold.body.Body;
import pt.isel.ls.view.htmlold.body.Table;
import pt.isel.ls.view.htmlold.head.Head;
import pt.isel.ls.view.htmlold.head.Title;

import java.util.ArrayList;
import java.util.List;

public class GetUserAllReviewsResult extends CommandResult {

    private final List<Model> reviews;

    public GetUserAllReviewsResult(List<Model> reviews) {
        this.reviews = reviews;
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
        for (Model r: reviews) {
            Review review = (Review) r;
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
                        new Title("User All Reviews")
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
        StringBuilder sb = new StringBuilder("User All Reviews -> \n");
        for (Model r : reviews) {
            Review review = (Review) r;
            sb.append("ReviewID = " + review.getId()
                    + "\nSummary = " + review.getSummary()
                    + "\nStars = " + review.getRating()
                    + "\nMovieID = " + review.getMovie().getMid()
                    + "\nMovie Critic = " + review.getMovieCritic().getName()
                    + "\n");
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
