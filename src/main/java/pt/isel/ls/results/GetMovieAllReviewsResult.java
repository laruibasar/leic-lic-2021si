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

public class GetMovieAllReviewsResult extends CommandResult {

    private final List<Model> reviews;

    public GetMovieAllReviewsResult(List<Model> reviews) {

        this.reviews = reviews;
    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Summary");
        header.add("Complete Review");
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
            sb.append("ReviewID = "
                    + review.getId()
                    + "\nSummary = "
                    + review.getSummary()
                    + "\nStars ="
                    + review.getRating()
                    + "\nMovieID = "
                    + review.getMovie()
                    + "\nMovieCritic = "
                    + review.getMovieCritic());
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
