package pt.isel.ls.view.text;

import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class GetMovieReviewTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();

        if (review == null) {
            return "Review not available";
        }

        StringBuilder sb = new StringBuilder("Movie Review:\n");
        sb.append("Critic =").append(review.getMovieCritic().getName()).append("\n");
        sb.append("MovieID =").append(review.getMovie().getMid()).append("\n");
        sb.append("UserID =").append(review.getMovieCritic().getId()).append("\n\n");
        sb.append("Score =").append(review.getRating()).append("\n");
        sb.append("Summary =").append(review.getSummary()).append("\n");
        sb.append("Full review =").append(review.getCompleteReview()).append("\n");

        return sb.toString();
    }
}
