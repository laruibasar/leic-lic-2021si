package pt.isel.ls.view.text;

import pt.isel.ls.model.Model;
import pt.isel.ls.model.Review;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.common.IView;

import java.util.LinkedList;

public class GetMovieAllReviewsTextView extends TextView implements IView {
    @Override
    public String print(Command cmd, CommandResult cr) {
        LinkedList<Review> reviews = (LinkedList<Review>) cr.getResult();

        StringBuilder sb = new StringBuilder("User All Reviews:\n");
        for (Model r : reviews) {
            Review review = (Review) r;
            sb.append("ReviewID = ").append(review.getId()).append("\n");
            sb.append("Summary = ").append(review.getSummary()).append("\n");
            sb.append("Stars =").append(review.getRating()).append("\n");
            sb.append("MovieID = ").append(review.getMovie().getMid()).append("\n");
            sb.append("Movie Critic = ").append(review.getMovieCritic().getId()).append("\n");
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
