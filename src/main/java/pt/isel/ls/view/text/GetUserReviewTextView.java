package pt.isel.ls.view.text;

import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class GetUserReviewTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();

        if (review == null) {
            return "User review details not available";
        }

        StringBuilder sb = new StringBuilder("User Review Details:\n");
        sb.append("ReviewId = ").append(review.getId()).append("\t");
        sb.append("Summary = ").append(review.getSummary()).append("\t");
        sb.append("Review = ").append(review.getCompleteReview());
        sb.append("Rating = ").append(review.getRating()).append("\t");
        sb.append("Movie Id = ").append(review.getMovie()).append("\t");
        sb.append("Movie Critic = ").append(review.getMovieCritic());

        return sb.toString();
    }
}
