package pt.isel.ls.results;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;

import java.util.ArrayList;
import java.util.List;

public class DeleteMovieReviewResult extends CommandResult {

    private Review review;

    public DeleteMovieReviewResult(List<Model> reviews) {
        if (reviews.size() != 0) {
            this.review = (Review) reviews.get(0);
        }

    }

    @Override
    public String printHtml() {
        ArrayList<String> header = new ArrayList<>();
        header.add("Id");
        header.add("Summary");
        header.add("Review");
        header.add("Movie Id");
        header.add("User Id");
        header.add("Rating");

        ArrayList<String[]> rows = new ArrayList<>();
        String title = "Deleted Review";
        if (review == null) {
            title = "Review not deleted";
        } else {
            rows.add(
                    new String[] {
                            String.valueOf(review.getId()),
                            review.getSummary(),
                            review.getCompleteReview(),
                            String.valueOf(review.getMovie()),
                            String.valueOf(review.getMovieCritic()),
                            String.valueOf(review.getRating())
                    }
            );
        }

//        Html h = new Html(
//                new Head(
//                        new Title(title)
//                ),
//                new Body(
//                        new Table(
//                                rows
//                        )
//                )
//
//        );
//        return h.toString();
        return null;
    }

    @Override
    public String printPlainText() {
        return review == null ? "Review not deleted" : "Deleted Review -> "
                + "ReviewID = " + review.getId()
                + "\nSummary = " + review.getSummary()
                + "\nComplete Review = " + review.getCompleteReview()
                + "\nStars = " + review.getRating()
                + "\nMovieID = " + review.getMovie().getMid()
                + "\nMovie Critic = " + review.getMovieCritic().getName();
    }
}
