package pt.isel.ls.view.text;

import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

public class DeleteMovieReviewTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        Review review = (Review) cr.getResult();
        return review == null ? "Review not deleted" : "Deleted Review -> "
                + "ReviewID = " + review.getId()
                + "\nSummary = " + review.getSummary()
                + "\nComplete Review = " + review.getCompleteReview()
                + "\nStars = " + review.getRating()
                + "\nMovieID = " + review.getMovie().getMid()
                + "\nMovie Critic = " + review.getMovieCritic().getName();
    }
}
